package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import java.util.Date;

/**
 * Represents a full message.
 * 
 * @author Tamas Kojedzinszky
 */
public abstract class FullMessage {

  protected long id;
  protected Person from;
  protected Type messageType;

  public FullMessage() {}
  
  public FullMessage(long id, Person from, Type messageType) {
    this.id = id;
    this.from = from;
    this.messageType = messageType;
  }

  public long getId() {
    return id;
  }

  public Person getFrom() {
    return from;
  }

  public abstract Date getDate();

  public Type getMessageType() {
    return messageType;
  }

  @Override
  public String toString() {
    return "FullMessage{" + "id=" + id + ", from=" + from + ", date=" + getDate() + ", messageType=" + messageType + '}';
  }
  
}
