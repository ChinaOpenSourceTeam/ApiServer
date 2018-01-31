/**   
* @Title: httpsProtocolUtil.java 
* @Package services.util 
* @author lyt
* @date 2013-12-13 上午10:25:31  
*/
package com.chinaopensource.apiserver.common.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/** 
 * @ClassName: httpsProtocolUtil 
 * @author lyt
 * @date 2013-12-13 上午10:25:31 
 *  
 */
public class HttpsProtocolUtil {
	public static String sendPost(String url, String param) throws IOException {
        OutputStream out = null;
        BufferedReader in = null;
        String str_return = "";
        try {
         SSLContext sc = SSLContext.getInstance("SSL");
         sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
           new java.security.SecureRandom());
         URL console = new URL(url);
         HttpsURLConnection conn = (HttpsURLConnection) console
           .openConnection();
         conn.setSSLSocketFactory(sc.getSocketFactory());
         conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
         conn.setDoOutput(true);
         conn.connect();
         if(null != param){
             out = conn.getOutputStream();  
             byte[] content = {};  
             content = param.getBytes("utf-8");  
             out.write(content);  
         }
         in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String line = null;
         while ((line = in.readLine()) != null) {
        	 str_return += new String(line.getBytes("ISO-8859-1"), "UTF-8");
         }
         conn.disconnect();
        } catch (ConnectException e) {
         System.out.println("ConnectException");
         System.out.println(e);
         throw e;
        } catch (IOException e) {
         System.out.println("IOException");
         System.out.println(e);
         throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
         try {
          out.close();
         } catch (Exception e) {
         }
        }
        return str_return;
    }
    
    public static String sendGet(String url) throws IOException {
        BufferedReader in = null;
        String result = "";
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.connect();
            
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result += new String(line.getBytes("ISO-8859-1"), "UTF-8");
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("sendGet Exception:" + e.getMessage());
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println("sendGet Exception:" + e.getMessage());
            }
        }
        return result;
    }
    
    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
