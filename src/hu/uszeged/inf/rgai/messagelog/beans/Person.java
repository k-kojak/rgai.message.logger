package hu.uszeged.inf.rgai.messagelog.beans;

import hu.uszeged.inf.rgai.messagelog.MessageProvider;
import java.util.LinkedList;
import java.util.List;

/**
 * This class holds the most important information of a user.
 * 
 * A unique id (depends on the type), a name and a type.
 *
 * @author Tamas Kojedzinszky
 */
public class Person {
  protected String id;
  protected String name;
  protected MessageProvider.Type type;

  
  public Person() {}
  
  public Person(String id, String name, MessageProvider.Type type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }
  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public MessageProvider.Type getType() {
    return type;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
  }
}
