package hu.uszeged.inf.rgai.messagelog;

import com.sun.mail.gimap.GmailFolder;
import com.sun.mail.gimap.GmailMessage;
import hu.uszeged.inf.rgai.messagelog.beans.account.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import hu.uszeged.inf.rgai.messagelog.beans.MessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.cert.CertPathValidatorException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.AuthenticationFailedException;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;
import javax.net.ssl.SSLHandshakeException;

/**
 * Specific Gmail type MessageProvider.
 * 
 * Gmail has its own extended API to communicate with imap servers, so it ismore flexible and 
 * has more features than the regular IMAP API.
 * 
 * @author Tamas Kojedzinszky
 */
public class GmailMessageProvider implements MessageProvider {

  private EmailAccount account;

  /**
   * Constructs a GmailMessageProvider object.
   * @param account the account information
   */
  public GmailMessageProvider(EmailAccount account) {
    this.account = account;
  }
  
  /**
   * Sets the system properties.
   */
  protected void setSystemProperties() {
    System.setProperty("java.net.preferIPv4Stack", "true");
  }
  
  @Override
  public List<MessageListElement> getMessageList(int offset, int limit) throws CertPathValidatorException, SSLHandshakeException,
          ConnectException, NoSuchProviderException, UnknownHostException, IOException, MessagingException, AuthenticationFailedException {
    return getMessageList(offset, limit, 20);
  }
  
  @Override
  public List<MessageListElement> getMessageList(int offset, int limit, int snippetMaxLength) throws CertPathValidatorException, SSLHandshakeException,
          ConnectException, NoSuchProviderException, UnknownHostException, IOException, MessagingException, AuthenticationFailedException {
    this.setSystemProperties();
    Properties props = System.getProperties();
    List<MessageListElement> emails = new LinkedList<MessageListElement>();
    
    props.put("mail.gimaps.ssl.checkserveridentity", "false");
    props.put("mail.gimaps.ssl.trust", "*");
    props.setProperty("mail.store.protocol", "gimaps");
    
    Session session = Session.getDefaultInstance(props, null);
    Store store = null;
    store = session.getStore("gimaps");
    store.connect(account.getImapAddress(), account.getEmail(), account.getPassword());

    GmailFolder folder = (GmailFolder)store.getFolder("Inbox");
    folder.open(Folder.READ_ONLY);
    int messageCount = folder.getMessageCount();
    int start = Math.max(1, messageCount - limit - offset + 1);
    int end = start + limit > messageCount ? messageCount : start + limit;
    Message messages[] = folder.getMessages(start, end);
    
    FetchProfile fp = new FetchProfile();
//    
    fp.add(GmailFolder.FetchProfileItem.MSGID);
    fp.add(GmailFolder.FetchProfileItem.THRID);
    fp.add(GmailFolder.FetchProfileItem.THRID);
    folder.fetch(messages, fp);


    for (int i = messages.length - 1; i >= 0; i--) {
      Message m = (GmailMessage)messages[i];
      
      String subject = m.getSubject();
      if (subject != null) {
        try {
          subject = MimeUtility.decodeText(subject);
        } catch (java.io.UnsupportedEncodingException ex) {
        }
      }
      boolean seen = m.isSet(Flags.Flag.SEEN);
      Date date = m.getSentDate();
      String from = m.getFrom()[0].toString();
      try {
        from = MimeUtility.decodeText(from);
      } catch (java.io.UnsupportedEncodingException ex) {
      }
      emails.add(new MessageListElement(m.getMessageNumber() + "", seen, subject,
              new Person("1", from, MessageProvider.Type.GMAIL), null, date, MessageProvider.Type.GMAIL));
    }
    folder.close(true);
    store.close();
    
    return emails;
  }

  @Override
  public FullSimpleMessage getMessage(String id) {
    return null;
  }

  @Override
  public void sendMessage(Set<? extends MessageRecipient> to, String content, String subject) throws
          NoSuchProviderException, MessagingException, IOException {
    
  }

  @Override
  public void markMessageAsRead(String id) throws NoSuchProviderException, MessagingException, IOException {
    // nothing to do here...
  }
}
