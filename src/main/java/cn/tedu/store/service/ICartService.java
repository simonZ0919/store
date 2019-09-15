package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.exception.AccessDeniedException;
import cn.tedu.store.exception.CartNotFoundException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.vo.CartVO;

public interface ICartService {
	
	/**
	 * update/add cart
	 * @param cart
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void add2Cart(String username, Cart cart) throws InsertException,UpdateException;
	
	/**
	 * add number of goods in cart
	 * @param id cart id 
	 * @param uid user id
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void addCount(Integer id, Integer uid) 
			throws CartNotFoundException,AccessDeniedException,UpdateException; 
	
	/**
	 * find cart/good data by user id
	 * @param uid
	 * @return list of cartVO
	 */
	List<CartVO> getByUid(Integer uid);
	
	/**
	 * find data by list of Id
	 * @param id
	 * @return data list
	 */
	List<CartVO> getByIdList(Integer[] id);
}
