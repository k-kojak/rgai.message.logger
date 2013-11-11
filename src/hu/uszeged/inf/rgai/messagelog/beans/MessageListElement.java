package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullSimpleMessage;
import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import hu.uszeged.inf.rgai.messagelog.beans.fullmessage.FullMessage;
import java.util.Date;

/**
 * This class represents a list element in the list of messages.
 * 
 * It holds as much information of a message in a list as possible.
 * 
 * @author Tamas Kojedzinszky
 */
public class MessageListElement /*implements Comparable<MessageListElement>*/ {
  
  protected String id;
  protected boolean seen;
  protected String title;
  protected String subTitle;
  protected int unreadCount;
  //TODO: this could be a list
  protected Person from;
  protected Date date;
  protected Type messageType;
  
  protected FullMessage fullMessage;

  /**
   * Constructor for a message element in a list.
   * 
   * @param id id of the message
   * @param seen <code>true</code> if the message is seen, <code>false</code> otherwise
   * @param title title of the message, can be <code>null</code>
   * @param subTitle subtitle of the message, can be <code>null</code>
   * @param from a Person object, the sender of the message
   * @param date date of the message
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for available types
   */
  public MessageListElement(String id, boolean seen, String title, String subTitle, int unreadCount, Person from, Date date, Type messageType) {
    this.id = id;
    this.seen = seen;
    this.title = title;
    this.from = from;
    this.date = date;
    this.subTitle = subTitle;
    this.unreadCount = unreadCount;
    this.messageType = messageType;
  }

  /**
   * Default constructor.
   */
  public MessageListElement() {
  }
  
  /**
   * Constructor for a message element in a list.
   * 
   * @param id id of the message
   * @param seen <code>true</code> if the message is seen, <code>false</code> otherwise
   * @param title title of the message, can be <code>null</code>
   * @param from a Person object, the sender of the message
   * @param date date of the message
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for available types
   */
  public MessageListElement(String id, boolean seen, String title, int unreadCount, Person from, Date date, Type messageType) {
    this(id, seen, title, null, unreadCount, from, date, messageType);
  }
  
  /**
   * Constructor for a message element in a list.
   * 
   * @param id id of the message
   * @param title title of the message, can be <code>null</code>
   * @param subTitle subtitle of the message, can be <code>null</code>
   * @param from a Person object, the sender of the message
   * @param date date of the message
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for available types
   */
  public MessageListElement(String id, boolean seen, String title, Person from, Date date, Type messageType) {
    this(id, seen, title, null, -1, from, date, messageType);
  }
  
  /**
   * Constructor for a message element in a list.
   * 
   * @param id id of the message
   * @param title title of the message, can be <code>null</code>
   * @param from a Person object, the sender of the message
   * @param date date of the message
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for available types
   */
//  public MessageListElement(String id, String title, Person from, Date date, Type messageType) {
//    this(id, true, title, null, -1, from, date, messageType);
//  }

  /**
   * Constructor for a message element in a list.
   * 
   * @param id id of the message
   * @param from a Person object, the sender of the message
   * @param date date of the message
   * @param messageType type of the message, see {@link hu.uszeged.inf.rgai.messagelog.MessageProvider.Type} for available types
   */
//  public MessageListElement(String id, Person from, Date date, Type messageType) {
//    this(id, true, null, null, -1, from, date, messageType);
//  }
  
  public String getId() {
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

  public int getUnreadCount() {
    return unreadCount;
  }

  public void setUnreadCount(int unreadCount) {
    this.unreadCount = unreadCount;
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

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public void setDate(Date date) {
    this.date = date;
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
    if (this.id.equals(other.id)) {
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
//    if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
//      return false;
//    }
    if (this.messageType != other.messageType) {
      return false;
    }
    return true;
  }

//  @Override
//  public int compareTo(MessageListElement o) {
//    if (this.equals(o)) {
//      return 0;
//    } else {
//      return -1 * this.date.compareTo(o.date);
//    }
//  }

  
  
}