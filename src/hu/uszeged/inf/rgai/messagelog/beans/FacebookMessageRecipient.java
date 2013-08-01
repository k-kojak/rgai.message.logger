package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FacebookMessageRecipient implements MessageRecipient {
  private long id;

  public FacebookMessageRecipient(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

}
