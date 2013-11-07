package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;
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
  
  public FullSimpleMessage(String id, String subject, String content, Date date, Person from, Type messageType, List<File> attachments) {
    super(id, subject, content, date, from, messageType, attachments);
  }

  @Override
  public String toString() {
    return "FullSimpleMessage{" + "id=" + id + ", from=" + from + ", date=" + getDate() + ", messageType=" + messageType + '}';
  }
  
}
