package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.exception.DeleteException;
import cn.tedu.store.exception.InsertException;

public interface IAddressService {
	/**
	 * create new address
	 * @param username
	 * @param address
	 * @return created address
	 * @throws InsertException
	 */
	Address create(String username,Address address) throws InsertException;
	
	/**
	 * get address by id
	 * @param uid
	 * @return found addresses
	 */
	List<Address> getListByUid(Integer uid);
	
	/**
	 * set default address
	 * @param uid userid
	 * @param id address id
	 */
	 void setDefault(Integer uid, Integer id);
	 
	 /**
	  * delete address by id 
	  * @param uid userid 
	  * @param id address id
	  */
	 void delete(Integer uid, Integer id) throws DeleteException;
}
