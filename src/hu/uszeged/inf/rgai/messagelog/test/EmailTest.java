package hu.uszeged.inf.rgai.messagelog.test;

import hu.uszeged.inf.rgai.messagelog.SimpleEmailMessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.account.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.EmailMessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullMessage;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertPathValidatorException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
    list = semp.getMessageList(0, 3);
    for (MessageListElement mle : list) {
      System.out.println(mle);
      System.out.println(mle.getFrom());
      FullSimpleMessage fsm = (FullSimpleMessage)mle.getFullMessage();
      System.out.println(fsm.getAttachments());
      System.out.println("");
    }
    
    byte[] data = semp.getAttachmentOfMessage("3503", "Meghívó_FuturICT.hu_Kutatásmenedzsment_képzés_2014. május.pdf");
    FileOutputStream fos = new FileOutputStream("./asd.pdf");
    fos.write(data);
    fos.close();
    
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
