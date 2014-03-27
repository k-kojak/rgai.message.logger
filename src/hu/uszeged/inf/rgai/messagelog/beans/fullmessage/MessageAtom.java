/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.Attachment;
import hu.uszeged.inf.rgai.messagelog.beans.HtmlContent;
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
  protected HtmlContent content;
  protected Date date;
  protected Person from;
  protected boolean isMe;
  protected MessageProvider.Type messageType;
  protected List<Attachment> attachments;

  public MessageAtom() {}
  
  /**
   * Constructor for an atom message.
   * 
   * @param content the content of the message with content mime type
   * @param date the date of the message
   * @param attachments attachments if is there any, can be <code>null</code>
   */
  public MessageAtom(String id, String subject, HtmlContent content, Date date, Person from,
          boolean isMe, MessageProvider.Type type, List<Attachment> attachments) {
    this.id = id;
    this.subject = subject;
    this.content = content;
    this.date = date;
    this.from = from;
    this.isMe = isMe;
    this.messageType = type;
    this.attachments = attachments;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public HtmlContent getContent() {
    return content;
  }

  public void setContent(HtmlContent content) {
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
  
  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isIsMe() {
    return isMe;
  }

  public void setIsMe(boolean isMe) {
    this.isMe = isMe;
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
