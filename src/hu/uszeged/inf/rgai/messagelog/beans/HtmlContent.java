package hu.uszeged.inf.rgai.messagelog.beans;

/**
 *
 * @author Tamas Kojedzinszky
 */
public class HtmlContent {

  public enum ContentType {
    TEXT("text/*"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html");
    
    private final String mimeType;
    
    ContentType(String mimeType) {
      this.mimeType = mimeType;
    }
    
    public String getMimeName() {
      return mimeType;
    }
  };
  
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
