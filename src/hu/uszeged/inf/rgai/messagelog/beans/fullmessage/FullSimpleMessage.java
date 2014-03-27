package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
import hu.uszeged.inf.rgai.messagelog.beans.Attachment;
import hu.uszeged.inf.rgai.messagelog.beans.HtmlContent;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Represents a full message.
 * 
 * @author Tamas Kojedzinszky
 */
public class FullSimpleMessage extends MessageAtom implements FullMessage {

  public FullSimpleMessage() {}
  
  public FullSimpleMessage(String id, String subject, HtmlContent content, Date date, Person from,
          boolean isMe, Type messageType, List<Attachment> attachments) {
    super(id, subject, content, date, from, isMe, messageType, attachments);
  }

  @Override
  public String toString() {
    return "FullSimpleMessage{" + "id=" + id + ", from=" + from + ", date=" + getDate() + ", messageType=" + messageType + '}';
  }
  
}
