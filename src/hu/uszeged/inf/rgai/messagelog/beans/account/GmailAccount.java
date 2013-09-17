package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;

/**
 * Holds a gmail account.
 * 
 * @author Tamas Kojedzinszky
 */
public class GmailAccount extends EmailAccount {

  public GmailAccount() {}
  
  public GmailAccount(String email, String password) {
    super(email, password, "imap.gmail.com", "smtp.gmail.com", 993, 455, true);
    this.accountType = Type.GMAIL;
  }
  
  @Override
  public String toString() {
    return "GmailAccount{" + "email=" + email + ", password=" + password + '}';
  }
  
  
  
}
