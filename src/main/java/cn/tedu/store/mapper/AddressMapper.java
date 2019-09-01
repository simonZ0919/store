package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	/**
	 * add new address
	 * @param address
	 * @return affected rows
	 */
	Integer addNew(Address address);
	
	/**
	 * count address by user id
	 * @param uid
	 * @return count numbers
	 */
	Integer countAddress(Integer uid);
}
