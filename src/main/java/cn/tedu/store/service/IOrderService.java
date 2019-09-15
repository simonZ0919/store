package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.vo.OrderVO;

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
	
	/**
	 * find order detail by id
	 * @param id
	 * @return detail of order
	 */
	OrderVO getById(Integer id);
}
