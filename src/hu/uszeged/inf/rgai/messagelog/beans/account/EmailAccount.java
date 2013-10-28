package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;

/**
 * Simple email account.
 * 
 * @author Tamas Kojedzinszky
 */
public class EmailAccount implements Account {
  protected String email;
  protected String password;
  protected String imapAddress;
  protected String smtpAddress;
  protected int imapPort;
  protected int smtpPort;
  protected boolean ssl;
  protected Type accountType;

  public EmailAccount() {}
  
  public EmailAccount(String email, String password, String imapAddress, String smtpAddress, int imapPort, int smtpPort) {
    this(email, password, imapAddress, smtpAddress, imapPort, smtpPort, true);
  }
  
  public EmailAccount(String email, String password, String imapAddress, String smtpAddress, int imapPort, int smtpPort, boolean ssl) {
    this.email = email;
    this.password = password;
    this.imapAddress = imapAddress;
    this.smtpAddress = smtpAddress;
    this.imapPort = imapPort;
    this.smtpPort = smtpPort;
    this.ssl = ssl;
    this.accountType = Type.EMAIL;
  }
  
  public EmailAccount(String email, String password, String imapAddress, String smtpAddress) {
    this(email, password, imapAddress, smtpAddress, 993, 455);
  }
  
  public EmailAccount(String email, String password, String imapAddress, String smtpAddress, boolean ssl) {
    this(email, password, imapAddress, smtpAddress, 993, 455, ssl);
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getImapAddress() {
    return imapAddress;
  }

  public String getSmtpAddress() {
    return smtpAddress;
  }

  public int getImapPort() {
    return imapPort;
  }

  public int getSmtpPort() {
    return smtpPort;
  }

  public boolean isSsl() {
    return ssl;
  }
  
  @Override
  public String toString() {
    return "EmailAccount{" + "email=" + email + ", password=" + password + ", imapAddress=" + imapAddress + ", smtpAddress=" + smtpAddress + ", imapPort=" + imapPort + ", smtpPort=" + smtpPort + '}';
  }

  @Override
  public Type getAccountType() {
    return accountType;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final EmailAccount other = (EmailAccount) obj;
    if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
      return false;
    }
    if (this.accountType != other.accountType) {
      return false;
    }
    return true;
  }

  @Override
  public String getDisplayName() {
    return email;
  }

}
