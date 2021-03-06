package com.nana.serviceengine.common.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpServiceRequest {
	/**
	 * 通过get方式请求，返回结果字符串
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url) {
		return httpGet(url,null);
	}

	/**
	 * 通过get方式请求，返回结果字符串
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url, String charSet) {
		url = url.replaceAll(" ", "%20");
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (charSet == null || "".equals(charSet))
						return EntityUtils.toString(entity);
					return EntityUtils.toString(entity, charSet);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
