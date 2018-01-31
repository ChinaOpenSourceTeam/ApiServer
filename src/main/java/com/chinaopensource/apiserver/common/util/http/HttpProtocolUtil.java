/**
 * @Title: HttpProtocolUtil.java
 * @Package services.util
 * @author lyt
 * @date 2013-12-13 下午4:28:14
 */
package com.chinaopensource.apiserver.common.util.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: HttpProtocolUtil
 * @author lyt
 * @date 2013-12-13 下午4:28:14
 * 
 */
public class HttpProtocolUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpProtocolUtil.class);

	private static final int CONNECTREQUESTTIMEOUT = 60*1000;
	private static final int CONNECTTIMEOUT = 60*1000;
	private static final int SOCKETTIMEOUT = 60*1000;

	
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            LOGGER.debug("请求的url {}",urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

	
	public static String sendPost(String url, String param) throws IOException {
		if (url.isEmpty()) {
			return null;
		}
		LOGGER.info("[Http Post请求][URL:{}]", url);
		HttpPost httpPost = new HttpPost(url);
		if (param != null) {
			StringEntity entity = new StringEntity(param, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTREQUESTTIMEOUT)
				.setConnectTimeout(CONNECTTIMEOUT).setSocketTimeout(SOCKETTIMEOUT).build();
		httpPost.setConfig(requestConfig);
		
		HttpClientBuilder httpclientBuilder =  HttpClientBuilder.create();
		httpclientBuilder.setDefaultRequestConfig(requestConfig);
		CloseableHttpClient closeableHttpClient = httpclientBuilder.build();
		HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
		return EntityUtils.toString(httpResponse.getEntity());
	}
	
	/**
	 * 发送请求
	 * 
	 * @param url
	 *            请求地址
	 * @param filePath
	 *            文件在服务器保存路径（这里是为了自己测试方便而写，可以将该参数去掉）
	 * @return
	 * @throws IOException
	 */
	public static String sendFile(String url, String filePath) throws IOException {
		String str_return = "";
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			return "";
		}
		// 第一部分
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // ////////必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		out.write(head);
		// 文件正文部分
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		try {
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				str_return = str_return + new String(line.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			LOGGER.error("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		return str_return;
	}

}
