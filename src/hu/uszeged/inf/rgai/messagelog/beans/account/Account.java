package hu.uszeged.inf.rgai.messagelog.beans.account;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;


/**
 * Interface for holding different account informations.
 * 
 * @author Tamas Kojedzinszky
 */
public interface Account {
  
  public Type getAccountType();
  
  @Override
  public boolean equals(Object account);
}
