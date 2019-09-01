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
	 * @return found user
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
	
	/**
	 * update avatar(path of image)
	 * @param id
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return affected rows
	 */
	Integer updateAvatar(
			@Param("id") Integer id,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * update user info(exclude password,avatar)
	 * @param user
	 * @return affected rows
	 */
	Integer updateInfo(User user);
}
