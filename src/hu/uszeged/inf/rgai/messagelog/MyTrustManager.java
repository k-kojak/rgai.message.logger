package hu.uszeged.inf.rgai.messagelog;

import com.sun.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class MyTrustManager implements X509TrustManager {

  public boolean isClientTrusted(X509Certificate[] cert) {
    return true;
  }

  public boolean isServerTrusted(X509Certificate[] cert) {
    return true;
  }

  public X509Certificate[] getAcceptedIssuers() {
    return new X509Certificate[0];
  }
}
