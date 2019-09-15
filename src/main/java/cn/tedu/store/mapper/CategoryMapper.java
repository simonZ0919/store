package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Category;


public interface CategoryMapper {
	/**
	 * find category by parentID
	 * @param parentId
	 * @return list of child
	 */
	List<Category> findByParent(Long parentId);
}
