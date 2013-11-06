package hu.uszeged.inf.rgai.messagelog.beans.fullmessage;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import hu.uszeged.inf.rgai.messagelog.beans.Person;
import java.util.Date;

/**
 *
 * @author Tamas Kojedzinszky
 */
public interface FullMessage {
  
  public String getId();
  public Person getFrom();
  public Date getDate();
  public MessageProvider.Type getMessageType();
}
