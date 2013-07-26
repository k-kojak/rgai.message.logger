package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * This class represents a full email message.
 * 
 * @author Tamas Kojedzinszky
 */
public class FullEmailMessage extends FullContentMessage {
  
  private String title;
  private List<File> attachments;
  private List<Person> to;

  public FullEmailMessage(String title, List<File> attachments, List<Person> to, String content, long id, Person from, Date date, MessageProvider.Type messageType) {
    super(content, id, from, date, messageType);
    this.title = title;
    this.attachments = attachments;
    this.to = to;
  }

  public String getTitle() {
    return title;
  }

  public List<File> getAttachments() {
    return attachments;
  }

  public List<Person> getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "FullEmailMessage{" + "id=" + id + ", from=" + from + ", title=" + title + ", attachments=" + attachments + ", to=" + to + ", date=" + date + ", messageType=" + messageType + "}\n"
            + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
            + content;
  }
  
  
  
}
