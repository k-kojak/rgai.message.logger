package hu.uszeged.inf.rgai.messagelog.beans;

import java.util.List;

/**
 * Simple email content.
 * 
 * @author Tamas Kojedzinszky
 */
public class EmailContent {
  
  private HtmlContent content;
  private List<Attachment> attachmentList;

  public EmailContent(HtmlContent content, List<Attachment> attachmentList) {
    this.content = content;
    this.attachmentList = attachmentList;
  }
  
  public EmailContent(HtmlContent content) {
    this(content, null);
  }

  public HtmlContent getContent() {
    return content;
  }
  
  public HtmlContent getContent(int maxLength) {
    return new HtmlContent(content.getContent().substring(0, Math.min(content.getContent().length(), maxLength)), content.getContentType());
  }

  public List<Attachment> getAttachmentList() {
    return attachmentList;
  }
  
}
