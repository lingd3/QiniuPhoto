package com.dan.photo.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dan.photo.domain.Image;
import com.dan.photo.domain.User;
import com.dan.photo.util.DBUtils;
import com.dan.photo.util.FileUtils;

/**
 * 图片服务类
 * @author gd
 *
 */
public class ImageService {
	
	/**
	 * 通过用户ID获取图片列表
	 * @param userId
	 * @return
	 */
	public ArrayList<Image> getByUserId(int userId) {
		ArrayList<Image> images = new ArrayList<Image>();
		String sql = "select id, name, url, date, user_id from image where user_id = ? order by date desc";
		String[] parameters = {userId + ""};
		List<Object[]> imageList = DBUtils.query(sql, parameters);
		for (Object[] objects : imageList) {
			Image image = new Image();
			image.setId(Integer.parseInt(objects[0].toString()));
			image.setName(objects[1].toString());
			image.setUrl(objects[2].toString());
			image.setDate((Date)objects[3]);
			image.setUser(new User(Integer.parseInt(objects[4].toString())));
			images.add(image);
		}
		return images;
	}
	
	/**
	 * 上传图片
	 * @param image
	 * @param inputStream
	 */
	public void addImage(Image image, InputStream inputStream) {
		FileUtils.upload(inputStream, image.getUrl());
		String[] sqls = {"insert into image(name, url, date, user_id) values(?, ?, ?, ?)"};
		String[][] parameters = {{image.getName(), image.getUrl(), new SimpleDateFormat("yyyy-MM-dd HH:mm").format(image.getDate()), image.getUser().getId() + ""}};
        DBUtils.updates(sqls, parameters);
	}
	
	/**
	 * 通过图片ID数组和图片Url数组删除图片
	 * @param ids
	 * @param urls
	 */
	public void delByIdsAndUrls(String ids, String urls) {
		String[] idArray = ids.split(",");
		String[] urlArray = urls.split(",");
		if (!"".equals(idArray[0]) && !"".equals(urlArray[0])) {
			String[] sqls = new String[idArray.length];
			String[][] parameters = new String[idArray.length][1];
			for (int i = 0; i < idArray.length; i++) {
				FileUtils.delete(urlArray[i]);
				sqls[i] = "delete from image where id = ?";
				parameters[i][0] = idArray[i];
			}
			DBUtils.updates(sqls, parameters);
		}
	}
	
}


