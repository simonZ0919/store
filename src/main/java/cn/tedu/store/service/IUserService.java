package cn.tedu.store.service;

import org.springframework.dao.DuplicateKeyException;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.UseerNotFoundException;

public interface IUserService {
	/**
	 * user signed in
	 * @param user 
	 * @return registered user
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws 
		DuplicateKeyException, InsertException;
	
	/**
	 * user login
	 * @param username
	 * @param password
	 * @return logined user
	 * @throws UseerNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) 
			throws UseerNotFoundException,PasswordNotMatchException;
}
