package cn.tedu.store.testcase;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Category;
import cn.tedu.store.service.ICategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCategoryService {
	@Autowired
	private ICategoryService service;
	
	@Test
	public void getByParent() {
		List<Category> categories=service.getByParent(1L);
		for (Category goodsCategory : categories) {
			System.out.println(goodsCategory);
		}
	}
}
