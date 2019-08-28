package cn.tedu.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
			String srcPwd=user.getPassword();
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

}
