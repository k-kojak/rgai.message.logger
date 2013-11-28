package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FullThreadMessage implements FullMessage {
  
  private Set<MessageAtom> messages = null;

  /**
   * Default constructor.
   */
  public FullThreadMessage() {
    messages = new TreeSet<MessageAtom>();
  }
  
  public FullThreadMessage(Set<MessageAtom> messages) {
    this.messages = messages;
  }
  
  public Set<MessageAtom> getMessages() {
    return messages;
  }

  public void addMessage(MessageAtom ma) {
    messages.add(ma);
  }
  
}
