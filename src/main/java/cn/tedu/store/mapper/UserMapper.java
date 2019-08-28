package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/**
	 * insert new user
	 * @param user
	 * @return affected rows
	 */
	Integer addnew(User user);
	
	/**
	 * find data by username
	 * @param username
	 * @return found user
	 */
	User findbyUsername(String username);
}
