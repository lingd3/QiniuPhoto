package com.dan.photo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片类
 * 
 * @author gd
 *
 */
public class Image implements Serializable {

	private int id; // 图片id
	private String name; // 图片名
	private String url; // 图片url
	private Date date; // 上传时间
	private User user; // 所属用户

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
