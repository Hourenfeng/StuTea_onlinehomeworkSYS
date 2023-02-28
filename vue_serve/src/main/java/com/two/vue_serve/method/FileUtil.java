package com.two.vue_serve.method;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件操作工具类
 */
public class FileUtil {
	/**
	 * 读取文件内容为二进制数组
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] read2ByteArray(String filePath) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(filePath).openConnection();
		connection.setReadTimeout(5000);
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		InputStream inputStream=null;
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			inputStream = connection.getInputStream();
		}
		byte[] data = inputStream2ByteArray(inputStream);
		inputStream.close();
		return data;
	}
	/**
	 * 流转二进制数组
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static byte[] inputStream2ByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}
}
