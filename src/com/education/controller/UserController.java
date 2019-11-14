package com.education.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.po.AdminUser;
import com.education.po.User;
import com.education.service.UserService;
import com.education.util.MessageCode;
import com.education.util.ReturnMessage;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/userLogin")
	public String toLogin(HttpServletRequest request) {
		
		return "login";
	}
	@RequestMapping("/admin")
	public String toAdminLogin() {
		return "back";
	}
	@RequestMapping("/backstagemanagesystem")
	public String toAdmin() {
		return "ami";
	}

	@RequestMapping("/userRegister")
	public String toRegister() {
		return "register";
	}
	@RequestMapping("/personal")
	public String toPersonal(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					return "person";
				}
			}
		}
		return "login";
	}
	@RequestMapping("/toLogin")
	@ResponseBody
	public ReturnMessage<String> verifyUser(User user,HttpServletResponse response){
		String message = userService.loginVerify(user);
		ReturnMessage<String> mes = new ReturnMessage<String>();
		if("404".equals(message)) {	
			mes.setData("不存在该用户");
			mes.setCode(404);
			mes.setMESSAGE("false");
		}else if ("500".equals(message)) {	
			mes.setData("用户名或密码错误");
			mes.setCode(500);
			mes.setMESSAGE("false");
		}else {		
			mes.setData(message);
			mes.setCode(200);
			Cookie cookie = new Cookie("token", message);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
		}
		return mes;
	}
	@RequestMapping("/adminLogin")
	@ResponseBody
	public ReturnMessage<String> adminLogin(AdminUser adminUser){
		String message = userService.adminLogin(adminUser);
		ReturnMessage<String> mes = new ReturnMessage<String>();
		if("404".equals(message)) {	
			mes.setData("不存在该用户");
			mes.setCode(404);
			mes.setMESSAGE("false");
		}else if ("500".equals(message)) {	
			mes.setData("用户名或密码错误");
			mes.setCode(500);
			mes.setMESSAGE("false");
		}else {		
			mes.setData("登陆成功");
			mes.setCode(200);
		}
		return mes;
	}
	@RequestMapping("/getUserByToken/{token}")
	@ResponseBody
	public ReturnMessage<String> getUserByToken(String token){
		User user = userService.findUserByToken(token);
		ReturnMessage<String> mes = new ReturnMessage<String>();
		if(user != null) {
			String username = user.getUsername();
			mes.setData(username);
		}else {
			mes.setCode(404);
			mes.setMESSAGE("false");
		}
		return mes;
	}
	@RequestMapping("/toRegister")
	@ResponseBody
	public ReturnMessage<String> register(HttpServletResponse response,User user){
		String message = userService.register(user);
		ReturnMessage<String> mes = new ReturnMessage<String>();
		if("exists".equals(message)) {
			mes.setData("已存在用户");
			mes.setCode(500);
			mes.setMESSAGE("false");
		}else {
			mes.setData("注册成功");
			mes.setCode(200);
			mes.setMESSAGE("true");
			String token = DigestUtils.md5DigestAsHex(user.getUsername().getBytes());
			Cookie cookie = new Cookie("token", token);
			response.addCookie(cookie);
		}
		return mes;
	}
	@RequestMapping("/autoLogin")
	@ResponseBody
	public ReturnMessage<String> autoLogin(HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
		ReturnMessage<String> mes = new ReturnMessage<String>();
		String verify = "false";
		String token = "";
		if(null != cookies) {
			for(Cookie cookie : cookies) {
				if("token".equals(cookie.getName())) {
					verify = userService.autoLogin(cookie.getValue());
					token = cookie.getValue();
					break;
				}
			}
		}
		if("true".equals(verify)){
			mes.setData(userService.findUserByToken(token).getUsername());
			mes.setCode(200);
			mes.setMESSAGE("login");
		}else {
			mes.setData("您还未登录!");
			mes.setCode(404);
			mes.setMESSAGE("unlogin");
		}
		return mes;
	}
}
