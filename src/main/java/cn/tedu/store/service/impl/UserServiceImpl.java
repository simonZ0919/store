package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired private UserMapper mapper;
	
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
