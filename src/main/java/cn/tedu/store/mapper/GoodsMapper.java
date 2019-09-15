package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	/**
	 * find good by category
	 * @param categoryId
	 * @param offset the first position selected
	 * @param count max number of goods
	 * @return
	 */
	List<Goods> findByCategory(@Param("categoryId") long categoryId, 
			@Param("offset") Integer offset, @Param("count") Integer count);
	
	/**
	 * find goods by id
	 * @param id
	 * @return entry of good
	 */
	Goods findById(long id);
	
	/**
	 * find goods order by top priority
	 * @param count max numbers of goods
	 * @return goods found
	 */
	List<Goods> findByPri(Integer count);
}
