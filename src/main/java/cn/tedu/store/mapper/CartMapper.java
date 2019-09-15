package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

public interface CartMapper {
	/**
	 * find cart by uid & gid
	 * @param uid
	 * @param goodsId
	 * @return cart data
	 */
	Cart findByUidAndGid(@Param("uid") Integer uid, 
			@Param("goodsId") Long goodsId);

	/**
	 * find cart data by id
	 * @param id
	 * @return found data
	 */
	Cart findById(Integer id);
	
	/**
	 * find cart/good data by user id
	 * @param uid
	 * @return list of cartVO
	 */
	List<CartVO> findByUid(Integer uid);
	
	/**
	 * find data by list of Id
	 * @param id
	 * @return data list
	 */
	List<CartVO> findByIdList(Integer[] ids);
	
	/**
	 * add new data to cart
	 * @param cart
	 * @return affected rpws
	 */
	Integer addnew(Cart cart);

	/**
	 * change count of product
	 * @param id
	 * @param count
	 * @return affected rows
	 */
	Integer updateCount(@Param("id") Integer id, 
			@Param("count") Integer count);
}
