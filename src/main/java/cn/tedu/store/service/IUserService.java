package cn.tedu.store.service;

import org.springframework.dao.DuplicateKeyException;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.exception.UserNotFoundException;

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
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) 
			throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * update password
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword(Integer id, String oldPwd, String newPwd)
		throws UserNotFoundException, PasswordNotMatchException, UpdateException;
}
