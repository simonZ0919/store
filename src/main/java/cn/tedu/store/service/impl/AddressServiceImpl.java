package cn.tedu.store.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired private AddressMapper mapper;

	@Override
	public Address create(String username, Address address) throws InsertException {
		Integer count=countAddress(address.getUid());
		// setdefault if first created
		address.setIsDefault(count==0?1:0);
		// set modified user/time
		address.setModifiedUser(username);
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedTime(new Date());
		//TODO district
		
		// insert address
		addNew(address);
		return address;
	}
	
	private void addNew(Address address) {
		Integer rows=mapper.addNew(address);
		if(rows!=1) {
			throw new InsertException("fail to add address");
		}
	}
	
	private Integer countAddress(Integer uid) {
		return mapper.countAddress(uid);
	}
	
}
