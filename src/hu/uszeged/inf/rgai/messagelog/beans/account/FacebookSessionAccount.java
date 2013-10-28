package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class FacebookSessionAccount implements Account {
  protected String displayName;
  protected String uniqueName;
  protected MessageProvider.Type accountType;
  
  public FacebookSessionAccount() {}

  public FacebookSessionAccount(String displayName, String uniqueName) {
    this.displayName = displayName;
    this.uniqueName = uniqueName;
    this.accountType = MessageProvider.Type.FACEBOOK;
  }

  public String getUniqueName() {
    return uniqueName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setUniqueName(String uniqueName) {
    this.uniqueName = uniqueName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final FacebookSessionAccount other = (FacebookSessionAccount) obj;
    if ((this.uniqueName == null) ? (other.uniqueName != null) : !this.uniqueName.equals(other.uniqueName)) {
      return false;
    }
    if (this.accountType != other.accountType) {
      return false;
    }
    return true;
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public MessageProvider.Type getAccountType() {
    return accountType;
  }

  @Override
  public String toString() {
    return "FacebookSessionAccount{" + "displayName=" + displayName + ", uniqueName=" + uniqueName + ", accountType=" + accountType + '}';
  }
  
}
