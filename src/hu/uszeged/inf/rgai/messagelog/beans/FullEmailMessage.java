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
public class FullEmailMessage extends FullMessage {
  
  private String title;
  private List<Person> to;
  private MessageAtom content;

  public FullEmailMessage(String title, List<File> attachments, List<Person> to, String content, long id, Person from, Date date, MessageProvider.Type messageType) {
    super(id, from, messageType);
    this.content = new MessageAtom(content, date, attachments);
    this.title = title;
    this.to = to;
  }

  public String getTitle() {
    return title;
  }

  public List<File> getAttachments() {
    return content.attachments;
  }

  public List<Person> getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "FullEmailMessage{" + "id=" + id + ", from=" + from + ", title=" + title + ", attachments=" + content.attachments + ", to=" + to + ", date=" + content.getDate() + ", messageType=" + messageType + "}\n"
            + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
            + content;
  }

  @Override
  public Date getDate() {
    return content.getDate();
  }
  
  public String getContent() {
    return content.getContent();
  }
  
}
