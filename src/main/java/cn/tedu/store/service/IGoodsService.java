package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	/**
	 * get good by category
	 * @param categoryId
	 * @param offset the first position selected
	 * @param count max number of goods
	 * @return
	 */
	List<Goods> getByCategory(long categoryId, Integer offset, Integer count);
	
	/**
	 * find goods by id
	 * @param id
	 * @return entry of good
	 */
	Goods getById(long id);
	
	/**
	 * find goods order by top priority
	 * @param count max numbers of goods
	 * @return goods found
	 */
	List<Goods> GetByPri(Integer count);
}
