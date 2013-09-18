package hu.uszeged.inf.rgai.messagelog.test;

import hu.uszeged.inf.rgai.messagelog.SimpleEmailMessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.account.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.EmailMessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertPathValidatorException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

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
    list = semp.getMessageList(0, 20);
   for (MessageListElement mle : list) {
      System.out.println(mle);
    }
//    System.out.println(semp.getMessage(1));
    //Set<EmailMessageRecipient> mr = new HashSet<EmailMessageRecipient>();
   // mr.add(new EmailMessageRecipient("k. tamas", "tamas.kojedzinszky@gmail.com"));
   // semp.sendMessage(mr, "my content, yiha...", "my subject, " + (Math.random()));
    
    
//    SimpleEmailMessageProvider semp = new SimpleEmailMessageProvider(new EmailAccount(user, pass, imap, smtp));
//    Set<EmailMessageRecipient> recipients = new HashSet<EmailMessageRecipient>();
//    recipients.add(new EmailMessageRecipient("kojak", "tamas.kojedzinszky@gmail.com"));
//    recipients.add(new EmailMessageRecipient("kojak7", "kojak7@vipmail.hu"));
////    System.out.println("");
//    semp.setTo2("kojak7@vipmail.hu");
//    semp.sendMessage(recipients, "Content", "Subject...", null);
    
  }
  
  public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException, CertPathValidatorException {
    new EmailTest();
  }
}
