/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class MessageAtom {
  
  private String content;
  private Date date;
  private Person from;
  private List<File> attachments;

  /**
   * Constructor for an atom message.
   * 
   * @param content the content of the message
   * @param date the date of the message
   * @param attachments attachments if is there any, can be <code>null</code>
   */
  public MessageAtom(String content, Date date, Person from, List<File> attachments) {
    this.content = content;
    this.date = date;
    this.from = from;
    this.attachments = attachments;
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
  
}
