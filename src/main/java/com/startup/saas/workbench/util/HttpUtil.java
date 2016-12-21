package com.startup.saas.workbench.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;

/**
 * http 访问工具类
 * 
 * @author lifeilong
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/** 默认数据编码：UTF-8 */
	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 发送http get请求，并得到响应结果，默认数据编码:UTF-8
	 * 
	 * @param url
	 *            访问地址
	 * @param paramsMap
	 *            参数
	 * @return
	 */
	public static String get(String url, Map<String, String> paramsMap) {
		return get(url, paramsMap, DEFAULT_CHARSET);
	}

	/**
	 * 发送http get请求，并得到响应结果
	 * 
	 * @param url
	 *            访问地址
	 * @param paramsMap
	 *            参数
	 * @param charset
	 *            编码方式
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String get(String url, Map<String, String> paramsMap, String charset) {

		logger.info("执行http get请求,url[{}],参数[{}],编码格式[{}]", new Object[] { url, paramsMap, charset });
		String result = "";

		// RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();
		// CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();// 实例化一个HttpClient
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		long start_time = System.currentTimeMillis();
		try {
			httpclient = HttpClients.createDefault();// 实例化一个HttpClient
			// 创建httpget
			HttpGet httpGet = new HttpGet(url);
			// 设置参数
			if (paramsMap != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry entry : paramsMap.entrySet()) {
					params.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
				}
				String str = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
				httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			}

			// 执行请求
			response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 获得返回的内容
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, charset);
			}
			logger.info("执行http get请求完成,response状态[{}],结果[{}],耗时[{}]",
					new Object[] { response.getStatusLine().getStatusCode(), result, (System.currentTimeMillis() - start_time) });
		} catch (Exception e) {
			logger.info("执行http get请求发生异常[{}],耗时[{}]", e.getMessage(), (System.currentTimeMillis() - start_time));
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				if (httpclient != null) {
					httpclient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送http post请求，并得到响应结果，默认数据编码:UTF-8
	 * 
	 * @param url
	 *            访问地址
	 * @param paramsMap
	 *            参数
	 * @return
	 */
	public static String post(String url, Map<String, String> paramsMap) {
		return post(url, paramsMap, DEFAULT_CHARSET);
	}

	/**
	 * 发送http post请求，并得到响应结果
	 * 
	 * @param url
	 *            访问地址
	 * @param paramsMap
	 *            参数
	 * @param charset
	 *            编码方式
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String post(String url, Map<String, String> paramsMap, String charset) {
		logger.info("执行http post请求,url[{}],参数[{}],编码格式[{}]", new Object[] { url, paramsMap, charset });
		String result = "";

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		long start_time = System.currentTimeMillis();

		try {
			httpclient = HttpClients.createDefault(); // 实例化一个HttpClient
			
			HttpPost httpost = new HttpPost(url);
			// 将参数传入post方法中
			if (paramsMap != null) {
				List<NameValuePair> formParams = new ArrayList<NameValuePair>();
				for (Map.Entry entry : paramsMap.entrySet()) {
					formParams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
				}
				httpost.setEntity(new UrlEncodedFormEntity(formParams, charset));
			}
			
			response = httpclient.execute(httpost); // 执行
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity(); // 返回服务器响应
				result = EntityUtils.toString(entity, charset); // 返回服务器响应的HTML代码
			}
			logger.info("执行http post请求完成,response状态[{}],结果[{}],耗时[{}]",
					new Object[] { response.getStatusLine().getStatusCode(), result, (System.currentTimeMillis() - start_time) });
		} catch (IOException e) {
			logger.info("执行http post请求发生异常[{}],耗时[{}]", e.getMessage(), (System.currentTimeMillis() - start_time));
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				if (httpclient != null) {
					httpclient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送http put请求，并得到响应结果，默认数据编码:UTF-8
	 * 
	 * @param url
	 *            访问地址
	 * @param param
	 *            参数(json)
	 * @return
	 */
	public static String put(String url, Map<String, String> paramsMap) {
		return put(url, paramsMap, null, DEFAULT_CHARSET);
	}

	/**
	 * 发送http put请求，并得到响应结果，默认数据编码:UTF-8
	 * 
	 * @param url
	 *            访问地址
	 * @param paramJson
	 *            参数(json)
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String put(String url, Map<String, String> paramsMap, String contentType) {
		return put(url, paramsMap, contentType, DEFAULT_CHARSET);
	}

	/**
	 * 发送http put请求，并得到响应结果
	 * 
	 * @param url
	 *            访问地址
	 * @param param
	 *            参数(json)
	 * @param contentType
	 *            内容类型
	 * @param charset
	 *            编码方式
	 * @return
	 */
	public static String put(String url, Map<String, String> paramsMap, String contentType, String charset) {
		logger.info("执行http put请求,url[{}],参数[{}],contentType[{}],编码格式[{}]", new Object[] { url, paramsMap, contentType, charset });
		String result = "";
		
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		long start_time = System.currentTimeMillis();

		try {
			httpclient = HttpClients.createDefault(); // 实例化一个HttpClient
			HttpPut httpPut = new HttpPut(url);
			// 将参数传入put方法中
			if (paramsMap != null) {
				if ("json".equals(contentType)) {
					httpPut.setHeader("Content-type", "application/json");
					JSONArray jsonArray = JSONArray.fromObject(paramsMap);
					httpPut.setEntity(new StringEntity(jsonArray.getString(0), charset));
				} else {
					httpPut.setHeader("Content-type", "application/x-www-form-urlencoded");
					List<NameValuePair> formParams = new ArrayList<NameValuePair>();
					for (Map.Entry entry : paramsMap.entrySet()) {
						formParams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
					}
					httpPut.setEntity(new UrlEncodedFormEntity(formParams, charset));
				}
			}
			
			response = httpclient.execute(httpPut); // 执行
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity(); // 返回服务器响应
				result = EntityUtils.toString(entity, charset); // 返回服务器响应的HTML代码
			}
			logger.info("执行http put请求完成,response状态[{}],结果[{}],耗时[{}]",
					new Object[] { response.getStatusLine().getStatusCode(), result, (System.currentTimeMillis() - start_time) });
		} catch (IOException e) {
			logger.info("执行http put请求发生异常[{}],耗时[{}]", e.getMessage(), (System.currentTimeMillis() - start_time));
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				if (httpclient != null) {
					httpclient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}