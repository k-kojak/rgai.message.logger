
package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class Attachment {
  private String fileName;
  private int size; // in bytes

  public Attachment(String fileName, int size) {
    this.fileName = fileName;
    this.size = size;
  }

  public String getFileName() {
    return fileName;
  }

  public int getSize() {
    return size;
  }
  
}
