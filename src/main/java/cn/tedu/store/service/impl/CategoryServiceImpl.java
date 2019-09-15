package cn.tedu.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Category;
import cn.tedu.store.mapper.CategoryMapper;
import cn.tedu.store.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryMapper mapper;
	
	@Override
	public List<Category> getByParent(Long parentId) {
		return findByParent(parentId);
	}
	
	private List<Category> findByParent(Long parentId) {
		return mapper.findByParent(parentId);
	}

}
