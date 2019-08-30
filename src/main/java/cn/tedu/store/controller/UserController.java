package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<Void> handleLogin(
			@RequestParam("username")String username,
			@RequestParam("password")String pwd,
			HttpSession session){
		User user=userService.login(username, pwd);
		// store data to session 
		session.setAttribute("id", user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/password.do")
	public ResponseResult<Void> changePassword(
			@RequestParam("old_password")String oldPwd,
			@RequestParam("new_password")String newPwd,
			HttpSession session){
		// get id from session 
		Integer id=getIdFromSession(session);
		userService.changePassword(id, oldPwd, newPwd);
		return new ResponseResult<Void>(SUCCESS);
	}
	
}
