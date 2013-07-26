package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import java.util.Date;

/**
 * This class (will probably?) hold the message information for facebook and sms messages.
 * 
 * 
 * @author Tamas Kojedzinszky
 */
public class FullContentMessage extends FullMessage {

  protected String content;

  public FullContentMessage(String content, long id, Person from, Date date, MessageProvider.Type messageType) {
    super(id, from, date, messageType);
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "FullContentMessage{" + "id=" + id + ", from=" + from + ", date=" + date + ", messageType=" + messageType + "}\n"
            + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
            + content;
            
  }

  
  
}
