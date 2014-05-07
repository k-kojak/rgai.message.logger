package hu.uszeged.inf.rgai.messagelog.test;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import hu.uszeged.inf.rgai.messagelog.SimpleEmailMessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.account.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.EmailMessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullMessage;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertPathValidatorException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import net.htmlparser.jericho.Source;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class EmailTest {
  
  public EmailTest() throws NoSuchProviderException, MessagingException, IOException, CertPathValidatorException {
    // Reading in account settings....
    Properties prop = new Properties();
    String user, pass, imap, smtp;
    user = pass = imap = smtp = "";
    try {
      prop.load(new InputStreamReader(new FileInputStream("../accounts.properties")));

      user = prop.getProperty("user");
      pass = prop.getProperty("pass");
      imap = prop.getProperty("imap");
      smtp = prop.getProperty("smtp");
    } catch (IOException e) {
      System.err.println("Az accounts file nem talalhato...");
      e.printStackTrace();
    }
    
    List<MessageListElement> list;
    
    // GMAIL
//    GmailMessageProvider gmp = new GmailMessageProvider(new EmailAccount(user, pass, imap, null));
//    list = gmp.getMessageList(0, 20);
//    for (MessageListElement mle : list) {
//      System.out.println(mle);
//    }
//    System.out.println(gmp.getMessage(500));
    
    
    // SIMPLE EMAIL
    SimpleEmailMessageProvider semp = new SimpleEmailMessageProvider(new EmailAccount(user, pass, imap, smtp, true));
//    semp.setAttachmentProgressUpdateListener(new SimpleEmailMessageProvider.AttachmentProgressUpdate() {
//
//      @Override
//      public void onProgressUpdate(int progress) {
//        System.out.println("PROGRESS: " + progress);
//      }
//    });
    Set<MessageListElement> loadedMsgs = new HashSet<MessageListElement>();
    loadedMsgs.add(new MessageListElement("795", true, "", "", -1, new Person("tamas.kojedzinszky@gmail.com", "", MessageProvider.Type.SMS), null, new Date(1399469199000l), MessageProvider.Type.EMAIL, true));
    loadedMsgs.add(new MessageListElement("794", true, "", "", -1, new Person("messages-noreply@linkedin.com", "", MessageProvider.Type.SMS), null, new Date(1399464534000l), MessageProvider.Type.EMAIL, true));
    loadedMsgs.add(new MessageListElement("793", true, "", "", -1, new Person("member@linkedin.com", "", MessageProvider.Type.SMS), null, new Date(1399460225000l), MessageProvider.Type.EMAIL, true));
    loadedMsgs.add(new MessageListElement("792", true, "", "", -1, new Person("hit-reply@linkedin.com", "", MessageProvider.Type.SMS), null, new Date(1399460207000l), MessageProvider.Type.EMAIL, true));
    loadedMsgs.add(new MessageListElement("791", true, "", "", -1, new Person("messages-noreply@linkedin.com", "", MessageProvider.Type.SMS), null, new Date(1399453332000l), MessageProvider.Type.EMAIL, true));
//    for (int i = 0; i < 5; i++) {
      list = semp.getMessageList(0, 5, loadedMsgs);
      for (MessageListElement mle : list) {
        System.out.println(mle);
        System.out.println(mle.getFrom());
//        FullSimpleMessage fsm = (FullSimpleMessage)mle.getFullMessage();
//        System.out.println(fsm.getAttachments());
        System.out.println("");
      }
//    }
    
//    System.out.println("SLEEP SOME...");
//    try {
//      Thread.sleep(5000);
//    } catch (InterruptedException ex) {
//      Logger.getLogger(EmailTest.class.getName()).log(Level.SEVERE, null, ex);
//    }
      
    
    
    
//    byte[] data = semp.getAttachmentOfMessage("3503", "Meghívó_FuturICT.hu_Kutatásmenedzsment_képzés_2014. május.pdf");
//    FileOutputStream fos = new FileOutputStream("./asd.pdf");
//    fos.write(data);
//    fos.close();
    
    
    
    
    
    
    
//    semp.markMessageAsRead("501");
    
    
    
    
    
    
    
    
//    FullSimpleMessage fms = (FullSimpleMessage) semp.getMessage("3258");
//    System.out.println(fms.toString());
//    System.out.println(fms.getContent());
//    System.out.println(semp.getMessage("3258"));
//    Set<EmailMessageRecipient> mr = new HashSet<EmailMessageRecipient>();
//    mr.add(new EmailMessageRecipient("k. tamas", "tamas.kojedzinszky@gmail.com"));
//    semp.sendMessage(mr, "my content, yiha...", "my subject, " + (Math.random()));
      
      
//    SimpleEmailMessageProvider semp = new SimpleEmailMessageProvider(new EmailAccount(user, pass, imap, smtp));
//    Set<EmailMessageRecipient> recipients = new HashSet<EmailMessageRecipient>();
//    recipients.add(new EmailMessageRecipient("kojak", "cojacksounds@gmail.com"));
//    recipients.add(new EmailMessageRecipient("kojak7", "kojak7@vipmail.hu"));
////    System.out.println("");
//    semp.setTo2("kojak7@vipmail.hu");
//    FullSimpleMessage fsm = (FullSimpleMessage)list.get(0).getFullMessage();
//    System.out.println(fsm.getContent().getContent());
//    Source source = new Source(fsm.getContent().getContent());
//    System.out.println(source.getRenderer().toString());
//    semp.sendMessage(recipients, "a " + source.getRenderer().toString(), "Subject...");
    
    
  }
  
  public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException, CertPathValidatorException {
    new EmailTest();
  }
}
