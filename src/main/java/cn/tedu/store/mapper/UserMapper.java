package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

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
	User findByUsername(String username);
	
	/**
	 * find user by id
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
	/**
	 * update password
	 * @param id
	 * @param password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return affected rows
	 */
	Integer updatePassword(
			@Param("id") Integer id,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
}
