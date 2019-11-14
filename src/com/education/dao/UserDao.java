package com.education.dao;

import org.apache.ibatis.annotations.Param;

import com.education.po.AdminUser;
import com.education.po.User;

public interface UserDao {
	public User findUser(@Param("username")String username);
	public User findUserByToken(@Param("token")String token);
	public void updatePassword(User user);
	public void insertUser(User user);
	public AdminUser findAdminUser(@Param("username")String username);
}
