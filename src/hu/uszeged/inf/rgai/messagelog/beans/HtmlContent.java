package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class HtmlContent {

  public enum ContentType {TEXT, TEXT_PLAIN, TEXT_HTML};
  
  protected StringBuilder content;
  protected ContentType contentType;
  
  

  public HtmlContent() {
    content = new StringBuilder();
    contentType = ContentType.TEXT_PLAIN;
  }

  public HtmlContent(String content, ContentType contentType) {
    this.content = new StringBuilder(content);
    this.contentType = contentType;
  }

  public StringBuilder getContent() {
    return content;
  }

  public ContentType getContentType() {
    return contentType;
  }
  
}
