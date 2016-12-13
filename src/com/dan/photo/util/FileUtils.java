package com.dan.photo.util;

import java.io.InputStream;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * 图片工具类（使用七牛云存储服务）
 * @author gd
 *
 */
public class FileUtils {

	private static final String ACCESS_KEY = ""; //七牛云密钥管理中的密钥
	private static final String SECRET_KEY = "";
	private static final String BUCKET_NAME = "photo"; //在七牛云创建的Bucket名字
	
	/**
	 * 上传图片到七牛云存储
	 * @param reader
	 * @param fileName
	 */
	public static void upload(InputStream reader, String fileName) {
		String uptoken;
		try {
			Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
			PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
			uptoken = putPolicy.token(mac);
			IoApi.Put(uptoken, fileName, reader, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除七牛云存储上的图片 
	 * @param key
	 */
	public static void delete(String key) {
		Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
		RSClient client = new RSClient(mac);
		client.delete(BUCKET_NAME, key);
	}
}

