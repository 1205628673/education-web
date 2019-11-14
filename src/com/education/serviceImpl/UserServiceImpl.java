package com.education.serviceImpl;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.dao.UserDao;
import com.education.po.AdminUser;
import com.education.po.User;
import com.education.service.UserService;
import org.springframework.util.DigestUtils;
@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		User user = userDao.findUser(username);
		return user;
	}
	@Override
	public User findUserByToken(String token) {
		User user = userDao.findUserByToken(token);
		return user;
	}
	@Override
	public String adminLogin(AdminUser adminUser) {
		String username = adminUser.getUsername();
		String password = adminUser.getPassword();
		AdminUser adminUser2 = userDao.findAdminUser(username);
		String verify = "false";
		if(adminUser2 == null) {
			verify = "404";
		}else if(adminUser2 != null && password.equals(adminUser2.getPassword())) {
			verify = "200";
		}else {
			verify = "500";
		}
		return verify;
	}

	@Override
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		userDao.updatePassword(user);
	}

	@Override
	public void insertuser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}
	public String register(User user) {
		User existsUser = null;
		try {
		String username = user.getUsername();
		String userMD5 = DigestUtils.md5DigestAsHex(user.getUsername().getBytes());
		String passwordMD5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		existsUser = findUser(username);
		if(existsUser == null) {
			User u = new User();
			u.setUsername(username);
			u.setPassword(passwordMD5);
			u.setToken(userMD5);
			insertuser(u);
			return "success";
		}else {
			return "exists";
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return "error";
		}
	}
	public String loginVerify(User inputUser) {
		String username = inputUser.getUsername();
		String passwordMD5 = DigestUtils.md5DigestAsHex(inputUser.getPassword().getBytes());
		String userMD5 = DigestUtils.md5DigestAsHex(inputUser.getUsername().getBytes());
		String verify = "false";
		User user = findUser(username);
		if(user == null) {
			verify = "404";
		}else if(user != null && passwordMD5.equals(user.getPassword())) {
			verify = userMD5;
		}else {
			verify = "500";
		}
		return verify;
	}
	public String autoLogin(String userMD5) {
		User user = findUserByToken(userMD5);
		if(userMD5.equals(user.getToken())) {
			return "true";
		}else {
			return "false";
		}
	}
}
