package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import java.util.Comparator;
import java.util.Date;

/**
 * This class represents a list element in the list of messages.
 * 
 * It holds as much information of a message in a list as possible.
 * 
 * @author Tamas Kojedzinszky
 */
public class MessageListElement implements Comparable<MessageListElement> {
  
  protected long id;
  protected boolean seen;
  protected String title;
  protected String subTitle;
  protected Person from;
  protected Date date;
  protected Type messageType;
  
  protected FullMessage fullMessage;

  public MessageListElement(long id, boolean seen, String title, String subTitle, Person from, Date date, Type messageType) {
    this.id = id;
    this.seen = seen;
    this.title = title;
    this.from = from;
    this.date = date;
    this.subTitle = subTitle;
    this.messageType = messageType;
  }

  public MessageListElement() {
  }
  
  public MessageListElement(long id, boolean seen, String title, Person from, Date date, Type messageType) {
    this(id, seen, title, null, from, date, messageType);
  }
  
  public MessageListElement(long id, String title, String subTitle, Person from, Date date, Type messageType) {
    this(id, true, title, subTitle, from, date, messageType);
  }
  
  public MessageListElement(long id, String title, Person from, Date date, Type messageType) {
    this(id, true, title, null, from, date, messageType);
  }
  
  public MessageListElement(long id, Person from, Date date, Type messageType) {
    this(id, true, null, null, from, date, messageType);
  }
  
  public long getId() {
    return id;
  }

  public boolean isSeen() {
    return seen;
  }

  public String getTitle() {
    return title;
  }

  public Person getFrom() {
    return from;
  }

  public Date getDate() {
    return date;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public Type getMessageType() {
    return messageType;
  }

  public FullMessage getFullMessage() {
    return fullMessage;
  }

  public void setFullMessage(FullMessage fullMessage) {
    this.fullMessage = fullMessage;
  }
  
  public void setSeen(boolean seen) {
    this.seen = seen;
  }

  @Override
  public String toString() {
    return "MessageListElement{" + "id=" + id + ", seen=" + seen + ", title=" + title + ", subTitle=" + subTitle + ", from=" + from + ", date=" + date + ", messageType=" + messageType + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MessageListElement other = (MessageListElement) obj;
    if (this.id != other.id) {
      return false;
    }
//    if (this.seen != other.seen) {
//      return false;
//    }
    if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
      return false;
    }
    if ((this.subTitle == null) ? (other.subTitle != null) : !this.subTitle.equals(other.subTitle)) {
      return false;
    }
    if (this.from != other.from && (this.from == null || !this.from.equals(other.from))) {
      return false;
    }
    if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
      return false;
    }
    if (this.messageType != other.messageType) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(MessageListElement o) {
    return -1 * this.date.compareTo(o.date);
  }

  
  
}