package hu.uszeged.inf.rgai.messagelog;

import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import hu.uszeged.inf.rgai.messagelog.beans.MessageListElement;
import hu.uszeged.inf.rgai.messagelog.beans.MessageRecipient;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullMessage;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.cert.CertPathValidatorException;
import java.util.List;
import java.util.Set;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.net.ssl.SSLHandshakeException;

/**
 * This is the global interface for a general Message Provider (simple email, gmail, facebook, etc).
 * @author Tamas Kojedzinszky
 */
public interface MessageProvider {
  
  public enum Type {EMAIL, GMAIL, FACEBOOK, SMS};
  
  /**
   * Returns the list of messages.
   * 
   * @param offset the offset of the queried messages
   * @param limit the limit of queried messages
   * @return List of MessageListElement objects, the list of messages
   * @throws CertPathValidatorException
   * @throws SSLHandshakeException
   * @throws ConnectException
   * @throws NoSuchProviderException
   * @throws UnknownHostException
   * @throws IOException
   * @throws MessagingException
   * @throws AuthenticationFailedException 
   */
  public List<MessageListElement> getMessageList(int offset, int limit) throws CertPathValidatorException,
          SSLHandshakeException, ConnectException, NoSuchProviderException, UnknownHostException,
          IOException, MessagingException, AuthenticationFailedException;
  
  /**
   * Returns the list of messages.
   * 
   * @param offset the offset of the queried messages
   * @param limit the limit of queried messages
   * @param snippetMaxLength the max length of the snippet
   * @return List of MessageListElement objects, the list of messages
   * @throws CertPathValidatorException
   * @throws SSLHandshakeException
   * @throws ConnectException
   * @throws NoSuchProviderException
   * @throws UnknownHostException
   * @throws IOException
   * @throws MessagingException
   * @throws AuthenticationFailedException 
   */
  public List<MessageListElement> getMessageList(int offset, int limit, int snippetMaxLength) throws CertPathValidatorException,
          SSLHandshakeException, ConnectException, NoSuchProviderException, UnknownHostException,
          IOException, MessagingException, AuthenticationFailedException;
  
  /**
   * Returns a single message.
   * 
   * @param id the id of the message
   * @return a FullMessage object which contains all necessary information about the message itself
   * @throws NoSuchProviderException
   * @throws MessagingException
   * @throws IOException 
   */
  public FullMessage getMessage(String id) throws NoSuchProviderException, MessagingException, IOException;
  
  /**
   * Sets the status of a message to seen.
   * 
   * @param id the id of the message
   * @throws NoSuchProviderException
   * @throws MessagingException
   * @throws IOException 
   */
  public void markMessageAsRead(String id) throws NoSuchProviderException, MessagingException, IOException;
  
  /**
   * Sends a message to the given recipient with the given content.
   * 
   * @param to set of recipients
   * @param content the content of the message
   * @param subject subject of the message (optional)
   * @throws NoSuchProviderException
   * @throws MessagingException
   * @throws IOException 
   */
  public void sendMessage(Set<? extends MessageRecipient> to, String content, String subject)
          throws NoSuchProviderException, MessagingException, IOException;
  
}