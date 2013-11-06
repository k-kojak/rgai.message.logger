package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.util.Date;

/**
 * Represents a full message.
 * 
 * @author Tamas Kojedzinszky
 */
public abstract class FullSimpleMessage implements FullMessage {

  protected String id;
  protected Person from;
  protected Type messageType;

  public FullSimpleMessage() {}
  
  public FullSimpleMessage(String id, Person from, Type messageType) {
    this.id = id;
    this.from = from;
    this.messageType = messageType;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Person getFrom() {
    return from;
  }

  @Override
  public Type getMessageType() {
    return messageType;
  }
  
  @Override
  public abstract Date getDate();

  @Override
  public String toString() {
    return "FullMessage{" + "id=" + id + ", from=" + from + ", date=" + getDate() + ", messageType=" + messageType + '}';
  }
  
}
