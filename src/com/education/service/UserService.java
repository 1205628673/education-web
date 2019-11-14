package com.education.service;

import com.education.po.AdminUser;
import com.education.po.User;

public interface UserService {
	public User findUser(String username);
	public User findUserByToken(String token);
	public void updatePassword(User user);
	public void insertuser(User user);
	public String register(User user);
	public String loginVerify(User inputUser);
	public String autoLogin(String userMD5);
	public String adminLogin(AdminUser adminUser);
}
