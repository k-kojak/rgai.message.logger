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
  protected Date date;
  protected Type messageType;

  public FullMessage(long id, Person from, Date date, Type messageType) {
    this.id = id;
    this.from = from;
    this.date = date;
    this.messageType = messageType;
  }

  public long getId() {
    return id;
  }

  public Person getFrom() {
    return from;
  }

  public Date getDate() {
    return date;
  }

  public Type getMessageType() {
    return messageType;
  }

  @Override
  public String toString() {
    return "FullMessage{" + "id=" + id + ", from=" + from + ", date=" + date + ", messageType=" + messageType + '}';
  }
  
}
