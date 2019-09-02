package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
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
}
