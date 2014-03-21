package hu.uszeged.inf.rgai.messagelog.beans;

import java.io.File;
import java.util.List;

/**
 * Simple email content.
 * 
 * @author Tamas Kojedzinszky
 */
public class EmailContent {
  
  private String content;
  private List<Attachment> attachmentList;

  public EmailContent(String content, List<Attachment> attachmentList) {
    this.content = content;
    this.attachmentList = attachmentList;
  }
  
  public EmailContent(String content) {
    this(content, null);
  }

  public String getContent() {
    return content;
  }
  
  public String getContent(int maxLength) {
    return content.substring(0, Math.min(content.length(), maxLength));
  }

  public List<Attachment> getAttachmentList() {
    return attachmentList;
  }
  
}
