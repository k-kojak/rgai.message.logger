/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class MessageAtom implements Comparable<MessageAtom> {
  
  protected String id;
  protected String subject;
  protected String content;
  protected Date date;
  protected Person from;
  protected MessageProvider.Type messageType;
  protected List<File> attachments;

  public MessageAtom() {}
  
  /**
   * Constructor for an atom message.
   * 
   * @param content the content of the message
   * @param date the date of the message
   * @param attachments attachments if is there any, can be <code>null</code>
   */
  public MessageAtom(String id, String subject, String content, Date date, Person from, MessageProvider.Type type, List<File> attachments) {
    this.id = id;
    this.subject = subject;
    this.content = content;
    this.date = date;
    this.from = from;
    this.messageType = type;
    this.attachments = attachments;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Person getFrom() {
    return from;
  }

  public void setFrom(Person from) {
    this.from = from;
  }
  
  public List<File> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<File> attachments) {
    this.attachments = attachments;
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MessageProvider.Type getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageProvider.Type messageType) {
    this.messageType = messageType;
  }

  @Override
  public int compareTo(MessageAtom o) {
    if (this.getId().equals(o.getId())) {
      return 0;
    } else {
      return this.getDate().compareTo(o.getDate());
    }
  }
  
}
