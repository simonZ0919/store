package cn.tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.exception.DuplicateKeyException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.ServiceException;

public abstract class BaseController {
	public static final Integer SUCCESS=200;
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		if (e instanceof DuplicateKeyException) {
			return new ResponseResult<>(400,e);
		} else if (e instanceof InsertException) {
			return new ResponseResult<>(500,e);
		}
		return null;
	}
}
