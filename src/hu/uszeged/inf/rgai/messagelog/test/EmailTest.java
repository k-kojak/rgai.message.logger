package hu.uszeged.inf.rgai.messagelog.test;

import hu.uszeged.inf.rgai.messagelog.GmailMessageProvider;
import hu.uszeged.inf.rgai.messagelog.SimpleEmailMessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.EmailAccount;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertPathValidatorException;
import java.util.List;
import java.util.Properties;
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
    String user, pass, imap;
    user = pass = imap = "";
    try {
      prop.load(new InputStreamReader(new FileInputStream("../accounts.properties")));

      user = prop.getProperty("user");
      pass = prop.getProperty("pass");
      imap = prop.getProperty("imap");
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
//    System.out.println(gmp.getMessage(267));
    
    
    
    
    // SIMPLE EMAIL
    SimpleEmailMessageProvider semp = new SimpleEmailMessageProvider(new EmailAccount(user, pass, imap, null));
    list = semp.getMessageList(0, 20);
    for (MessageListElement mle : list) {
      System.out.println(mle);
    }
//    System.out.println(semp.getMessage(28));
    
    
  }
  
  public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException, CertPathValidatorException {
    new EmailTest();
  }
}
