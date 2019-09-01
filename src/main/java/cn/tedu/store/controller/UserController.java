package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.exception.FileEmptyException;
import cn.tedu.store.exception.FileSizeOutOfBoundException;
import cn.tedu.store.exception.FileTypeNotSupportException;
import cn.tedu.store.exception.FileUploadException;
import cn.tedu.store.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	// name of upload foler
	private static final String UPLOAD_DIR = "upload";
	
	private static final int MAX_FILE_SIZE = 10*1024*1024;
	
	private static final List<String> FILE_TYPE =new ArrayList<>();
	//initialzie list
	static {
		FILE_TYPE.add("image/jpeg");
		FILE_TYPE.add("image/png");
	}
	
	@Autowired private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(
			@RequestParam("username")String username,
			@RequestParam("password")String pwd,
			HttpSession session){
		User user=userService.login(username, pwd);
		// store data to session 
		session.setAttribute("id", user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<>(SUCCESS,user);
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(HttpSession session, 
			@RequestParam("file") MultipartFile file){
		// check empty file
		if (file.isEmpty()) {
			throw new FileEmptyException("empty file");
		}
		// check file size
		if(file.getSize()>MAX_FILE_SIZE) {
			throw new FileSizeOutOfBoundException("file size too large");
		}
		// check file type
		if (!FILE_TYPE.contains(file.getContentType())) {
			throw new FileTypeNotSupportException("file type not support");
		}
		//TODO
		// path to store upload file 
		String parentPath=session.getServletContext().getRealPath(UPLOAD_DIR);
		System.out.println(parentPath);
		File parent=new File(parentPath);
		// if not exist, create new folder
		if(!parent.exists()) {
			parent.mkdir();
		}
		// get file extension and rename file
		String oldName=file.getOriginalFilename();
		String ext=oldName.substring(oldName.lastIndexOf("."));
		String childName=System.currentTimeMillis()+ext;
		File dest=new File(parent, childName);
		// transfer file
		try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			throw new FileUploadException("file upload error");
		}
		// store path to db
		Integer id=getIdFromSession(session);
		String avatar="/"+UPLOAD_DIR+"/"+childName;
		userService.changeAvatar(id, avatar);
		// reuturn result 
		ResponseResult<String> rr=new ResponseResult<String>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
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
	
	@RequestMapping("/info.do")
	public ResponseResult<User> getInfo(HttpSession session){
		User data=userService.getById(getIdFromSession(session));
		return new ResponseResult<User>(SUCCESS,data);
	}
	
	@PostMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user, HttpSession session){
		// set id to user
		user.setId(getIdFromSession(session));
		userService.changeInfo(user);
		return new ResponseResult<Void>(SUCCESS);
	}
}
