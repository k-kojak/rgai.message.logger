package hu.uszeged.inf.rgai.messagelog;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPInputStream;
import com.sun.mail.smtp.SMTPTransport;
import hu.uszeged.inf.rgai.messagelog.beans.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.EmailContent;
import hu.uszeged.inf.rgai.messagelog.beans.EmailMessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.FullEmailMessage;
import hu.uszeged.inf.rgai.messagelog.beans.FullMessage;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import hu.uszeged.inf.rgai.messagelog.beans.MessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.cert.CertPathValidatorException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.net.ssl.SSLHandshakeException;

/**
 * Implements a simple email message providing via IMAP protocol.
 * 
 * This class (in theory) can handle all email account types which supports IMAP.
 * 
 * @author Tamas Kojedzinszky
 */
public class SimpleEmailMessageProvider implements MessageProvider {

  private EmailAccount account;
  private String attachmentFolder = "../files/";

  /**
   * Constructs a SimpleEmailMessageProvider object.
   * 
   * @param account the account to connect with
   * @param attachmentFolder path to folder where to save attachments
   */
  public SimpleEmailMessageProvider(EmailAccount account, String attachmentFolder) {
    this.account = account;
    this.attachmentFolder = attachmentFolder;
  }
  
  /**
   * Constructs a SimpleEmailMessageProvider object.
   * 
   * @param account the account to connect with
   */
  public SimpleEmailMessageProvider(EmailAccount account) {
    this.account = account;
  }
  
  /**
   * Sets properties for the given object.
   * 
   * @param props the property to set
   */
  protected void setProperties(Properties props) {
    System.setProperty("java.net.preferIPv4Stack", "true");
    props.setProperty("mail.imap.port", "993");
    props.put("mail.imaps.ssl.checkserveridentity", "false");
    props.put("mail.imaps.ssl.trust", "*");
    props.setProperty("mail.store.protocol", "imaps");
  }
  
  /**
   * Connects to imap, returns a store.
   * 
   * @return the store where, where you can read the emails
   * @throws NoSuchProviderException
   * @throws MessagingException
   * @throws AuthenticationFailedException 
   */
  protected Store getStore() throws NoSuchProviderException, MessagingException, AuthenticationFailedException {
    Properties props = System.getProperties();
    this.setProperties(props);
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect(account.getImapAddress(), account.getEmail(), account.getPassword());
    
    return store;
  }
  
  @Override
  public List<MessageListElement> getMessageList(int offset, int limit) throws CertPathValidatorException, SSLHandshakeException,
          ConnectException, NoSuchProviderException, UnknownHostException, IOException, MessagingException, AuthenticationFailedException {
    
    List<MessageListElement> emails = new LinkedList<MessageListElement>();
    Store store = this.getStore();

    IMAPFolder inbox = (IMAPFolder)store.getFolder("Inbox");
    inbox.open(Folder.READ_ONLY);
    int messageCount = inbox.getMessageCount();
    int start = Math.max(1, messageCount - limit - offset + 1);
    int end = start + limit > messageCount ? messageCount : start + limit;
    Message messages[] = inbox.getMessages(start, end);

    for (int i = messages.length - 1; i >= 0; i--) {
      Message m = messages[i];
      
      String subject = m.getSubject();
      if (subject != null) {
        subject = replaceUnknownCharacterType(subject);
        try {
          subject = MimeUtility.decodeText(subject);
        } catch (java.io.UnsupportedEncodingException ex) {
        }
      }
      boolean seen = m.isSet(Flags.Flag.SEEN);
      Date date = m.getSentDate();
      String from = null;
      if (m.getFrom() != null) {
        from = m.getFrom()[0].toString();
        from = replaceUnknownCharacterType(from);
      }
      if (from != null) {
        try {
          from = MimeUtility.decodeText(from);
        } catch (java.io.UnsupportedEncodingException ex) {
        }
      }
      
      // Skipping email from listing, because it is a spam probably,
      // ...at least citromail does not give any information about the email in some cases,
      // and in web browsing it displays an "x" sign before the title, which may indicate
      // that this is a spam
      if (from == null) {
        // skipping email
      } else {
        emails.add(new MessageListElement((long)(m.getMessageNumber()), seen, subject, new Person(1, from), date, Type.EMAIL));
      }
    }
    inbox.close(true);
    store.close();
    
    return emails;
  }
  
  /**
   * Replaces the "x-unknown" encoding type with "iso-8859-2" to be able to decode the mime string.
   * 
   * @param text a mime encoded raw text
   * @return a changed raw text with corrected encoding
   */
  private static String replaceUnknownCharacterType(String text) {
    if (text.indexOf("=?x-unknown?") != -1) {
      text = text.replace("x-unknown", "iso-8859-2");
    }
    return text;
  }
  
  /**
   * Extracts the content of an email message.
   * 
   * Since the content's MIME type varies, the extraction has different ways.
   * 
   * @param fullMessage the full message content
   * @return the extracted content of an email message
   * @throws MessagingException
   * @throws IOException 
   */
  protected EmailContent getMessageContent(Message fullMessage) throws MessagingException, IOException {
    
//    System.setProperty("javax.activation.debug", "true");
    StringBuilder content = new StringBuilder();
    List<File> attachments = null;
    
    Object msg = fullMessage.getContent();
    
    if (fullMessage.isMimeType("text/*")) {
      /*if (msg instanceof IMAPInputStream) {
        IMAPInputStream imapIs = (IMAPInputStream) msg;
        InputStream dis = MimeUtility.decode(imapIs, "base64");

        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        String line;
        while ((line = br.readLine()) != null) {
          content.append(line);
        }
      } else {*/
        content = new StringBuilder((String) msg);
      /*}*/
    } else if (fullMessage.isMimeType("multipart/*")) {
      /*if (msg instanceof IMAPInputStream) {
        IMAPInputStream imapIs = (IMAPInputStream) msg;
        InputStream dis = MimeUtility.decode(imapIs, "binary");

        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        String line;
        while ((line = br.readLine()) != null) {
          content.append(line);
        }
      } else*/ if (msg instanceof Multipart) {
        Multipart mp = (Multipart) msg;
        content = new StringBuilder(getContentOfMultipartMessage(mp, 0));
//        attachments = getAttachmentsOfMultipartMessage(mp, 0);
        
      }/* else {
        System.out.println("Nem tudom 1...");
      }*/
        
    } else if (msg instanceof IMAPInputStream) {
      IMAPInputStream imapIs = (IMAPInputStream) msg;
      InputStream dis = MimeUtility.decode(imapIs, "binary");
      
      BufferedReader br = new BufferedReader(new InputStreamReader(dis));
      String line;
      while ((line = br.readLine()) != null) {
        content.append(line);
      }
    } else {
      System.out.println("Nem tudom 2");
    }
    return new EmailContent(content.toString(), attachments);
  }
  
  /**
   * Returns an attachment list for a given Mime Multipart object.
   * 
   * @param mp the input Mime part of the email's content
   * @param level debug variable
   * @return list of files where the attachments are saved
   * @throws MessagingException
   * @throws IOException 
   */
  private List<File> getAttachmentsOfMultipartMessage(Multipart mp, int level) throws MessagingException, IOException {
    List<File> files = new LinkedList<File>();

//    System.out.println("\ngetAttachmentsOfMultipartMessageLevel -> " + level);
    
    for (int j = 0; j < mp.getCount(); j++) {
      
      Part bp = mp.getBodyPart(j);
      String contentType = bp.getContentType().toLowerCase();
      
      if (!Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
        if (contentType.indexOf("multipart/") != -1) {
          files.addAll(getAttachmentsOfMultipartMessage((Multipart)(bp.getContent()), level + 1));
        }
        continue;
      } else {
//        System.out.println("Not ignoring...");
      }
      
      InputStream is = bp.getInputStream();
      File f = new File(this.attachmentFolder + bp.getFileName());
      FileOutputStream fos = new FileOutputStream(f);
      byte[] buf = new byte[4096];
      int bytesRead;
      while((bytesRead = is.read(buf))!=-1) {
          fos.write(buf, 0, bytesRead);
      }
      fos.close();
      files.add(f);
      // Give some initial date to content, to not return with null, so we can debug later
      
    }
    
    return files;
  }
  
  /**
   * Returns the pure String content of a Mime Multipart message.
   * 
   * @param mp the input Mime part of the email's content
   * @param level debug variable
   * @return the pure String content of a Mime Multipart message
   * @throws MessagingException
   * @throws IOException 
   */
  private String getContentOfMultipartMessage(Multipart mp, int level) throws MessagingException, IOException {
    StringBuilder content = new StringBuilder("");
    
//    System.out.println("getContentOfMultipartMessageLevel -> " + level);
    
    for (int j = 0; j < mp.getCount(); j++) {
      
      Part bp = mp.getBodyPart(j);
      String contentType = bp.getContentType().toLowerCase();
      // Give some initial date to content, to not return with null, so we can debug later
      if (content.length() == 0) {
        content = new StringBuilder(bp.getContent().toString());
      }
      
      if (contentType.indexOf("multipart/") != -1) {
        content = new StringBuilder(getContentOfMultipartMessage((Multipart)(bp.getContent()), level + 1));
      } else if (contentType.indexOf("text/plain") != -1 && content.length() == 0) {
        content = new StringBuilder(bp.getContent().toString());
      } else if (contentType.indexOf("text/html") != -1) {
        content = new StringBuilder(bp.getContent().toString());
        break;
      }
    }
    
    return content.toString();
  }
  
  /**
   * Extracts the person information from Address object.
   * 
   * @param a the raw input Address object from the email
   * @return Person object
   */
  private static Person getPersonFromAddress(Address a) {
    String ad = a.toString();
    
    ad = replaceUnknownCharacterType(ad);
    try {
      ad = MimeUtility.decodeText(ad);
    } catch (UnsupportedEncodingException ex) {
      ex.printStackTrace();
    }
    
    int mailOp = ad.indexOf("<");
    int mailCl = ad.indexOf(">");
    String name = null;
    String email;
    if (mailOp != -1 && mailOp + 1 < mailCl) {
      email = ad.substring(mailOp + 1, mailCl);
      name = ad.substring(0, mailOp).trim();
    } else {
      email = ad.trim();
    }
    return new Person(1, name, email);
  }

  @Override
  public FullMessage getMessage(long id) throws NoSuchProviderException, MessagingException, IOException {

    Store store = this.getStore();
    IMAPFolder folder;
    EmailContent content;
    folder = (IMAPFolder)store.getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    
    Message ms = folder.getMessage((int)id);
    
    List<Person> to = new LinkedList<Person>();
    
    Address[] addr = ms.getAllRecipients();
    for (Address a : addr) {
      to.add(getPersonFromAddress(a));
    }
    content = getMessageContent(ms);
    
    Person from = getPersonFromAddress(ms.getFrom()[0]);
    
    String subject = ms.getSubject();
    
    folder.close(true);
    store.close();
    
    return new FullEmailMessage(subject, content.getAttachmentList(), to, content.getContent(),
            (long)(ms.getMessageNumber()), from, ms.getSentDate(), Type.EMAIL);
    
  }

  @Override
  public void sendMessage(Set<? extends MessageRecipient> to, String content, String subject) throws
          NoSuchProviderException, MessagingException, IOException, AddressException {
    
    Properties props = System.getProperties();
    props.put("mail.smtps.host", account.getSmtpAddress());
    props.put("mail.smtps.auth","true");
    Session session = Session.getInstance(props, null);
    javax.mail.Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(account.getEmail()));
    
    String addressList = getAddressList(to);

    msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(addressList));
    msg.setSubject(subject);
    msg.setText(content);
//    msg.setHeader("X-Mailer", "");
    msg.setSentDate(new Date());
    SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
    t.connect(account.getSmtpAddress(), account.getEmail(), account.getPassword());
    t.sendMessage(msg, msg.getAllRecipients());
    t.close();
    
  }
  
  private static String getAddressList(Set<? extends MessageRecipient> to) {
    StringBuilder sb = new StringBuilder();
    for (MessageRecipient p : to) {
      EmailMessageRecipient er = (EmailMessageRecipient)p;
      if (er.getEmail().length() > 0) {
        if (sb.toString().length() > 0) {
          sb.append(", ");
        }
        if (er.getName() != null && er.getName().length() > 0) {
          sb.append(er.getName());
        }
        sb.append("<").append(er.getEmail()).append(">");
      }
    }
    
    return sb.toString();
  }
}
