package cn.tedu.store.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.exception.AccessDeniedException;
import cn.tedu.store.exception.CartNotFoundException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.vo.CartVO;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired private CartMapper mapper;
	
	@Override
	public void add2Cart(String username, Cart cart) 
			throws InsertException, UpdateException {
		Cart data=findByUidAndGid(cart.getUid(), cart.getGid());
		// if data has been added or not
		if (data==null) {
			cart.setCreatedUser(username);
			cart.setModifiedUser(username);
			cart.setCreatedTime(new Date());
			cart.setModifiedTime(new Date());
			addnew(cart);
		} else {// add count in db and count on order
			Integer count=data.getCount()+cart.getCount();
			updateCount(data.getId(), count);
		}
	}
	
	@Override
	public void addCount(Integer id, Integer uid) 
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		Cart data=findById(id);
		if (data == null) {
			throw new CartNotFoundException("no record found");
		}
		if (!data.getUid().equals(uid)) {
			throw new AccessDeniedException("parent not matched");
		}
		
		Integer count=data.getCount()+1;
		updateCount(id, count);
	}
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	@Override
	public List<CartVO> getByIdList(Integer[] ids) {
		return findByIdList(ids);
	}
	
	private Cart findByUidAndGid(Integer uid, Long goodsId) {
		return mapper.findByUidAndGid(uid, goodsId);
	}


	private void addnew(Cart cart) {
		Integer rows=mapper.addnew(cart);
		if (rows!=1) {
			throw new InsertException("fail to create cart");
		}
	};

	private void updateCount(Integer id,  Integer count) {
		Integer rows=mapper.updateCount(id, count);
		if (rows!=1) {
			throw new UpdateException("fail to update cart");
		}
	}
	
	private List<CartVO> findByUid(Integer uid) {
		return mapper.findByUid(uid);
	}
	
	private Cart findById(Integer id) {
		return mapper.findById(id);
	}
	
	private List<CartVO> findByIdList(Integer[] ids){
		return mapper.findByIdList(ids);
	}

}
