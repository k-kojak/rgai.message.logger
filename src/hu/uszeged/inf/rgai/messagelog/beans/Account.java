package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider.Type;


/**
 * Interface for holding different account informations.
 * 
 * @author Tamas Kojedzinszky
 */
public interface Account {
  
  public Type getAccountType();
  public boolean equals(Account a);
}
