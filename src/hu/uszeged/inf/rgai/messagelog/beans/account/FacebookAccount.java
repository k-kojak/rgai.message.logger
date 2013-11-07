package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;

/**
 * Class for Facebook account.
 * 
 * @author Tamas Kojedzinszky
 */
public class FacebookAccount implements Account {
  protected String displayName;
  protected String uniqueName;
  protected String id;
  protected String password;
  protected Type accountType;

  public FacebookAccount() {}
  
  public FacebookAccount(String displayName, String uniqueName, String id, String password) {
    this.displayName = displayName;
    this.uniqueName = uniqueName;
    this.id = id;
    this.password = password;
    this.accountType = Type.FACEBOOK;
  }

  public String getPassword() {
    return password;
  }
  
  @Override
  public Type getAccountType() {
    return accountType;
  }
  
  @Override
  public String getDisplayName() {
    return displayName;
  }

  public String getUniqueName() {
    return uniqueName;
  }

  public String getId() {
    return id;
  }
  
  @Override
  public String toString() {
    return "FacebookAccount{" + "dispalyName=" + displayName + ", uniqueName=" + uniqueName + ", accountType=" + accountType + '}';
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
    if ((this.displayName == null) ? (other.displayName != null) : !this.displayName.equals(other.displayName)) {
      return false;
    }
    if (this.accountType != other.accountType) {
      return false;
    }
    return true;
  }

  
  
}
