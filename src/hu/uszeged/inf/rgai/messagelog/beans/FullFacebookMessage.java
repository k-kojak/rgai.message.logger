/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FullFacebookMessage extends FullMessage {
  
  private List<MessageAtom> messages = null;
  
  public FullFacebookMessage(long id, Person from, Type messageType, List<MessageAtom> messages) {
    super(id, from, messageType);
    this.messages = messages;
  }

  public List<MessageAtom> getMessages() {
    return messages;
  }

  @Override
  public Date getDate() {
    if (messages != null && !messages.isEmpty()) {
      return messages.get(0).getDate();
    } else {
      return null;
    }
  }
  
}
