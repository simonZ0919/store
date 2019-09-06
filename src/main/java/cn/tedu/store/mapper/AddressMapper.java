package cn.tedu.store.mapper;

import java.util.List;

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
	
	/**
	 * get address by id
	 * @param uid
	 * @return found addresses
	 */
	List<Address>findById(Integer uid);
	
	/**
	 * update nondefault
	 * @param uid user id
	 * @return affected rows
	 */
	Integer updateNonDefault(Integer uid);
	
	/**
	 * update to default
	 * @param id
	 * @return affected rows
	 */
	Integer updateDefault(Integer id);
}
