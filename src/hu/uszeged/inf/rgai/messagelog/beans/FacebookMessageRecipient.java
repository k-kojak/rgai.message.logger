package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FacebookMessageRecipient implements MessageRecipient {
  private String id;

  public FacebookMessageRecipient(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

}
