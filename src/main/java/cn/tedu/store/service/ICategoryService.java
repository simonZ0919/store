package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Category;

public interface ICategoryService {
	/**
	 * find good categories by parentid
	 * @param parentId
	 * @return list of categories
	 */
	List<Category> getByParent(Long parentId);
}
