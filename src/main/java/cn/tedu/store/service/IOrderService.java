package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.exception.InsertException;

public interface IOrderService {
	/**
	 * create order 
	 * @param uid
	 * @param username
	 * @param addressId
	 * @param cartIds
	 * @return
	 */
	Order createOrder(Integer uid, String username,
		    Integer addressId, Integer[] cartIds) throws InsertException;
}
