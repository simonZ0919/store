package cn.tedu.store.service;

import org.springframework.dao.DuplicateKeyException;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;

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
}
