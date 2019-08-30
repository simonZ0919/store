package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.exception.DuplicateKeyException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.ServiceException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.exception.UserNotFoundException;

public abstract class BaseController {
	public static final Integer SUCCESS=200;
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer state=null;
		if (e instanceof DuplicateKeyException) {
			state=400;
		} else if (e instanceof UserNotFoundException  ) {
			state=401;
		}else if (e instanceof PasswordNotMatchException) {
			state=402;
		}else if (e instanceof InsertException) {
			state=500;
		}else if (e instanceof UpdateException) {
			state=501;
		}
		return new ResponseResult<>(state,e);
	}
	
	/**
	 * get id from httpsession
	 * @param session
	 * @return id
	 */
	protected Integer getIdFromSession(HttpSession session) {
		return Integer.valueOf(
				session.getAttribute("id").toString());
	}
}
