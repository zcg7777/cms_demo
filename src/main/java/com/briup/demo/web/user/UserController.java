package com.briup.demo.web.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.briup.demo.common.bean.User;
import com.briup.demo.common.exception.UserServcieException;
import com.briup.demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/")
	public String hello() {
		System.out.println("--------------------");
		return "hello";
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute User user) {
		
		System.out.println("注册用户: " + user);
		try {
			//注册用户 如果成功往下走，如果失败 抛出异常
			userService.register(user);
			
			return "login";
		} catch (UserServcieException e) {
			e.printStackTrace();
			//注册失败 跳转到注册页面
			return "register";
		}
		
		
	}
	
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username,@RequestParam("password")String password,Model model){
		try {
			User user = userService.login(username, password);
			System.out.println("user: " + user);
			model.addAttribute("loginUser", user);
			
			System.out.println("登录成功，准备跳转");
			
			//登录成功，跳转到user_page,获取类别信息，然后跳转到主页进行加载
			return "redirect:/user/user_page";
		} catch (UserServcieException e) {
			e.printStackTrace();
			//登录不成功，跳转回去 登录页面
			return "login";
		}
	}
	
	@RequestMapping("/user_page")
	public void user_page() {
		
	}
	
	
	
	
	
}











