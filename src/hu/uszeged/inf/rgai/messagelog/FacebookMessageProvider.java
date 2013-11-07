package hu.uszeged.inf.rgai.messagelog;

import hu.uszeged.inf.rgai.messagelog.beans.account.FacebookAccount;
import hu.uszeged.inf.rgai.messagelog.beans.FacebookMessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullFacebookMessage;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.MessageAtom;
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
import java.util.Set;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.net.ssl.SSLHandshakeException;

/**
 *
 * @author Tamas Kojedzinszky
 * @deprecated 
 */
public class FacebookMessageProvider implements MessageProvider {

  // use this variable to access facebook
  private FacebookAccount account;
  
  public FacebookMessageProvider(FacebookAccount account) {
    this.account = account;
  }
  
  @Override
  public List<MessageListElement> getMessageList(int offset, int limit) throws CertPathValidatorException,
          SSLHandshakeException, ConnectException, NoSuchProviderException, UnknownHostException, IOException,
          MessagingException, AuthenticationFailedException {
    // EXAMPLE CODE FOR PETI
    List<MessageListElement> messages = new LinkedList<MessageListElement>();
    // getting sender information, just give a random id, and the real name of the user
    Person sender = new Person(1, "Kis Zoltán");
    
    messages.add(new MessageListElement("1", false, "Title", "Subtitle", sender, new Date(), Type.FACEBOOK));
    
    sender = new Person(2, "Nagy Aladár");
    messages.add(new MessageListElement("1", false, "Title2", "Subtitle2", sender, new Date(), Type.FACEBOOK));
    
    return messages;
  }

  @Override
  public FullSimpleMessage getMessage(String id) throws NoSuchProviderException, MessagingException, IOException {
    // EXAMPLE CODE FOR PETI
    Person sender = new Person(3, "Zelk Zoltán");
//    FullFacebookMessage ffm = new FullFacebookMessage(id, sender, Type.EMAIL);
//    
//    ffm.addMessage(new MessageAtom("This is the content of a message item...", new Date(), null, null));
//    ffm.addMessage(new MessageAtom("This is the content of another...", new Date(), null, null));
//    ffm.addMessage(new MessageAtom("This is the third message...", new Date(), null, null));
    
    return null;
  }

  @Override
  public void sendMessage(Set<? extends MessageRecipient> to, String content, String subject) throws
          NoSuchProviderException, MessagingException, IOException {
    // EXAMPLE CODE FOR PETI
    
    for (MessageRecipient mr : to) {
      FacebookMessageRecipient fmr = (FacebookMessageRecipient)mr;
      
      // sending message here to facebook user using the information in FacebookMessageRecipient class
    }
  }
  
}
