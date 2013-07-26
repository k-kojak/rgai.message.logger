package hu.uszeged.inf.rgai.messagelog.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * In the future this class should represent a Contact in a smart phone.
 *
 * @author Tamas Kojedzinszky
 */
public class Person {
  private int id;
  private String name;
  private List<String> emails;

  public Person(int id, String name, List<String> emails) {
    this.id = id;
    this.name = name;
    this.emails = emails;
  }
  
  public Person(int id, String name) {
    this(id, name, new LinkedList<String>());
  }
  
  public Person(int id, String name, String email) {
    this(id, name, new LinkedList<String>());
    addEmail(email);
  }
  
  public final void addEmail(String email) {
    if (!emails.contains(email)) {
      emails.add(email);
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<String> getEmails() {
    return emails;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name=" + name + ", emails=" + emails + '}';
  }
  
}
