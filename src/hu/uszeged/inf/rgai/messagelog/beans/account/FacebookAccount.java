package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;

/**
 * Class for Facebook account.
 * 
 * @author Tamas Kojedzinszky
 */
public class FacebookAccount implements Account {
  protected String userName;
  protected String password;
  protected Type accountType;

  public FacebookAccount() {}
  
  public FacebookAccount(String userName, String password) {
    this.userName = userName;
    this.password = password;
    this.accountType = Type.FACEBOOK;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "FacebookAccount{" + "userName=" + userName + ", password=" + password + '}';
  }

  @Override
  public Type getAccountType() {
    return accountType;
  }

  @Override
  public int hashCode() {
    int hash = 3;
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
    final FacebookAccount other = (FacebookAccount) obj;
    if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
      return false;
    }
    if (this.accountType != other.accountType) {
      return false;
    }
    return true;
  }
  
}
