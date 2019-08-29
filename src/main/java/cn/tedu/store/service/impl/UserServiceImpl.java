package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.DuplicateKeyException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.UseerNotFoundException;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired private UserMapper mapper;
	
	@Override
	public User login(String username, String password) 
			throws UseerNotFoundException, PasswordNotMatchException {
		User data=findByUsername(username);
		if (data == null) {// if user not exist
			throw new UseerNotFoundException(
					"username: "+username+" not exist");
		}
		String salt=data.getSalt();
		String md5Pwd=getMd5Password(password, salt);
		if(data.getPassword().equals(md5Pwd)) {
			if(data.getIsDelete()==1) {
				throw new UseerNotFoundException("account has been deleted");
			}
			// clear data and return userinfo
			data.setPassword(null);
			data.setSalt(null);
			return data;
		}else {
			throw new PasswordNotMatchException("password incorrect");
		}
	}	
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		User data=findByUsername(user.getUsername());
		if (data == null) {// if name not used, insert user
			// set default entries
			user.setIsDelete(0);
			Date now=new Date();
			user.setCreatedUser(user.getUsername());
			user.setModifiedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedTime(now);
			
			// get password for encrypt
			String srcPwd=user.getPassword();
			// get uuid as salt 	
			String salt=UUID.randomUUID().toString();
			String md5Password=getMd5Password(srcPwd, salt);
			user.setPassword(md5Password);
			user.setSalt(salt);
			
			addNew(user);
			return user;
		}else {// if user exists, throw exception 
			throw new DuplicateKeyException(
					"username: "+user.getUsername()+" has been used");
		}
	}
	
	private void addNew(User user) {
		Integer rows=mapper.addnew(user);
		if(rows!=1) {
			throw new InsertException("unknown system error"); 
		}
	}
	
	private User findByUsername(String username) {
		return mapper.findByUsername(username);
	}
	
	// compute key from password and salt
	private String getMd5Password(String pwd, String salt) {
		// concat sat and pwd
		String output=salt+pwd+salt;
		// iterate for 5 times
		for (int i = 0; i < 5; i++) {
			output=DigestUtils.md5DigestAsHex(output.getBytes());
		}
		return output;
	}

}
