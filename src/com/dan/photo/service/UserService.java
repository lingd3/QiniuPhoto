package com.dan.photo.service;

import java.util.List;

import com.dan.photo.domain.User;
import com.dan.photo.util.DBUtils;

/**
 * 用户服务类
 * @author gd
 */
public class UserService {

	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username) {
		String sql = "select id, username, password from user where username = ?";
		String[] parameters = {username};
		List<Object[]> users = DBUtils.query(sql, parameters);
		if (users.size() == 0) {
			return null;
		} else {
			Object[] objects = users.get(0);
			return objects == null ? null
					: new User(Integer.parseInt(objects[0].toString()), objects[1].toString(), objects[2].toString(), null);
		}
	}

	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user) {
		String[] sqls = { "insert into user(username, password) values(?,?)" };
		String[][] parameters = { { user.getUsername(), user.getPassword() } };
		DBUtils.updates(sqls, parameters);
	}

}
