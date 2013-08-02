/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FullFacebookMessage extends FullSimpleMessage {
  
  private List<MessageAtom> messages = null;

  /**
   * Default constructor.
   */
  public FullFacebookMessage() {}
  
  /**
   * Constructor for a full facebook message.
   * 
   * @param id id of the facebook message thread
   * @param from the sender
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for details
   * @param messages the message elements
   */
  public FullFacebookMessage(long id, Person from, Type messageType, List<MessageAtom> messages) {
    super(id, from, messageType);
    this.messages = messages;
  }
  
  /**
   * Constructor for a full facebook message.
   * 
   * @param id id of the facebook message thread
   * @param from the sender
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for details
   */
  public FullFacebookMessage(long id, Person from, Type messageType) {
    this(id, from, messageType, new LinkedList<MessageAtom>());
  }

  public List<MessageAtom> getMessages() {
    return messages;
  }
  
  /**
   * Adds a new message to the list.
   * 
   * @param ma a new message element
   */
  public void addMessage(MessageAtom ma) {
    this.addMessage(ma);
  }

  /**
   * Returns the date of the last message if exists.
   * 
   * @return the date of the last message if exists
   */
  @Override
  public Date getDate() {
    if (messages != null && !messages.isEmpty()) {
      return messages.get(0).getDate();
    } else {
      return null;
    }
  }
  
}
