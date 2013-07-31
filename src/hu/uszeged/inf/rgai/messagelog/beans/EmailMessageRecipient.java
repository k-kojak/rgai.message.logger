package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class EmailMessageRecipient implements MessageRecipient {
  
  String name;
  String email;

  public EmailMessageRecipient(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
