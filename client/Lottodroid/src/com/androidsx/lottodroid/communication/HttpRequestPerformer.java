package com.androidsx.lottodroid.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Performs an HTTP request using URLConnection API
 * 
 */
class HttpRequestPerformer {

  /** Time to wait to the server until the connection is canceled, it is specified in milliseconds */
  private static int CONNECT_TIMEOUT = 10 * 1000; // 10 seconds looks like a reasonable amount
  
  /** Time to wait while reading data once the connection is stablished */
  private static int READ_TIMEOUT = CONNECT_TIMEOUT;
  
  /**
   * Forms an HTTP request
   * 
   * @param url The URL to request for.
   * @return The response as a String
   * @throws IOException 
   * @throws MalformedURLException 
   */
  public static String getResponse(String url) throws MalformedURLException, IOException,
      UnknownHostException, SocketTimeoutException {
    HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
    http.setRequestMethod("POST");
    http.setDoInput(true);
    http.setConnectTimeout(CONNECT_TIMEOUT);
    http.setReadTimeout(READ_TIMEOUT);

    try {
      return toString(http.getInputStream(), "ISO-8859-1");
    } finally {
      http.getInputStream().close();

      if (http.getErrorStream() != null)
        http.getErrorStream().close();

      http.disconnect();
    }

  }
  
  /**
   * Forms an HTTP request
   * 
   * @param url The URL to request for.
   * @param encoding The kind of code format.
   * @return The response as a String
   * @throws IOException 
   * @throws MalformedURLException 
   */
  public static String getResponse(String url, String encoding) throws MalformedURLException, IOException,
      UnknownHostException, SocketTimeoutException {
    HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
    http.setRequestMethod("POST");
    http.setDoInput(true);
    http.setConnectTimeout(CONNECT_TIMEOUT);
    http.setReadTimeout(READ_TIMEOUT);

    try {
      return toString(http.getInputStream(), encoding);
    } finally {
      http.getInputStream().close();

      if (http.getErrorStream() != null)
        http.getErrorStream().close();

      http.disconnect();
    }

  }

  /**
   * Reads an InputStream and returns its contents as a String. Also effects rate control.
   * 
   * @param inputStream The InputStream to read from.
   * @return The contents of the InputStream as a String.
   * @throws IOException 
   */
  private static String toString(InputStream inputStream, String encoding) throws IOException {
    StringBuilder outputBuilder = new StringBuilder();
    String string = null;
    
    if (inputStream != null) {
      // http.getContentEncoding is null, but we must decode the data in the charset we received
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));
      while (null != (string = reader.readLine())) {
        outputBuilder.append(string);
      }
    }

    return outputBuilder.toString();
  }
}
