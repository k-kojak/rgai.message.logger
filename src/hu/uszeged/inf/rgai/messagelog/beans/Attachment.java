
package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class Attachment {
  private String fileName;
  private long size; // in bytes

  public Attachment(String fileName, long size) {
    this.fileName = fileName;
    this.size = size;
  }

  public String getFileName() {
    return fileName;
  }

  public long getSize() {
    return size;
  }
  
  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "Attachment{" + "fileName=" + fileName + ", size=" + size + '}';
  }
  
}
