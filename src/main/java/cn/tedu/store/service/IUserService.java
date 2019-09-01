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
	User reg(User user) throws DuplicateKeyException, InsertException;

	/**
	 * user login
	 * @param username
	 * @param password
	 * @return logined user
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

	/**
	 * get user by id
	 * @param id
	 * @return user data
	 */
	User getById(Integer id);

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
	
	/**
	 * update avatar
	 * @param id
	 * @param avatar
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer id, String avatar)
			throws UserNotFoundException, UpdateException;
	
	/**
	 * change user info
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotFoundException, UpdateException;

}
