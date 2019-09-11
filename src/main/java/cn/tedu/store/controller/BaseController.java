package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.exception.AccessDeniedException;
import cn.tedu.store.exception.AddressNotFoundException;
import cn.tedu.store.exception.DuplicateKeyException;
import cn.tedu.store.exception.FileEmptyException;
import cn.tedu.store.exception.FileSizeOutOfBoundException;
import cn.tedu.store.exception.FileTypeNotSupportException;
import cn.tedu.store.exception.FileUploadException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.RequestException;
import cn.tedu.store.exception.ServiceException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.exception.UserNotFoundException;

public abstract class BaseController {
	public static final Integer SUCCESS=200;
	@ExceptionHandler({ServiceException.class, RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer state=null;
		if (e instanceof DuplicateKeyException) {
			state=400;
		}else if (e instanceof UserNotFoundException  ) {
			state=401;
		}else if (e instanceof PasswordNotMatchException) {
			state=402;
		}else if (e instanceof AddressNotFoundException  ) {
			state=403;
		}else if (e instanceof AccessDeniedException) {
			state=404;
		}else if (e instanceof InsertException) {
			state=500;
		}else if (e instanceof UpdateException) {
			state=501;
		}else if (e instanceof FileEmptyException) {
			state=600;
		}else if (e instanceof FileSizeOutOfBoundException) {
			state=601;
		}else if (e instanceof FileTypeNotSupportException) {
			state=602;
		}else if (e instanceof FileUploadException) {
			state=610;
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
