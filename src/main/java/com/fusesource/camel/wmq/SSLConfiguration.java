package com.fusesource.camel.wmq;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLConfiguration {

  private String keystore = null;
  private String keystorePassword = "";
  private String truststore = null;
  private SSLSocketFactory SSLSocketFactory = null;

  public SSLSocketFactory createSSLSocketFactory(String keyStorePath, String trustStorePath, char[] keystorePassword, char[] keyManagerPassword)
      throws Exception {

    KeyManagerFactory keyManagerFactory = null;
    if (keyStorePath != null) {

      // Create a keystore object for the keystore
      KeyStore keyStore = KeyStore.getInstance("JKS");

      // Open our file and read the keystore

      try (FileInputStream keyStoreInput = new FileInputStream(keyStorePath)) {
        keyStore.load(keyStoreInput, keystorePassword);
      }

      // Build key manager factory
      keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      keyManagerFactory.init(keyStore, keyManagerPassword);
    }

    TrustManagerFactory trustManagerFactory = null;
    if (trustStorePath != null) {
      // Create a keystore object for the truststore
      KeyStore trustStore = KeyStore.getInstance("JKS");
      // Open our file and read the truststore (no password)

      try (FileInputStream trustStoreInput = new FileInputStream(trustStorePath)) {
        trustStore.load(trustStoreInput, null);
      }
      // Create a default trust and key manager
      trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      // Initialise the managers
      trustManagerFactory.init(trustStore);
    }

    // Get an SSL context.
    SSLContext sslContext = SSLContext.getInstance("SSL");

    // Initialise our SSL context from the key/trust managers
    if (trustManagerFactory != null) {
      if (keyManagerFactory != null) {
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
      } else {
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
      }
    } else {
      if (keyManagerFactory != null) {
        sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
      } else { // Get the default keystore/truststore from Java
        sslContext.init(null, null, new SecureRandom());
      }
    }
    return sslContext.getSocketFactory();
  }

  public String getKeystore() {
    return keystore;
  }

  public void setKeystore(String keystore) {
    this.keystore = keystore;
  }

  public String getTruststore() {
    return truststore;
  }

  public void setTruststore(String truststore) {
    this.truststore = truststore;
  }

  public String getKeystorePassword() {
    return keystorePassword;
  }

  public void setKeystorePassword(String keystorePassword) {
    this.keystorePassword = keystorePassword;
  }

  public SSLSocketFactory getSSLSocketFactory() throws Exception {
    if (SSLSocketFactory == null) {
      SSLSocketFactory = createSSLSocketFactory(keystore, truststore, keystorePassword.toCharArray(), keystorePassword.toCharArray());
    }
    return SSLSocketFactory;
  }

  public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
    SSLSocketFactory = sSLSocketFactory;
  }

}
