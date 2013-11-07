package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tamas Kojedzinszky
 * @deprecated 
 */
public class FullFacebookMessage implements FullMessage {
  
  private List<MessageAtom> messages = null;

  /**
   * Default constructor.
   */
  public FullFacebookMessage() {
    messages = new LinkedList<MessageAtom>();
  }
  
  public List<MessageAtom> getMessages() {
    return messages;
  }

  public void addMessage(MessageAtom ma) {
    messages.add(ma);
  }
  
}
