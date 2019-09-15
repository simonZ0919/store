package cn.tedu.store.testcase;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Category;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.CategoryMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCategoryDB {
	@Autowired
	CategoryMapper mapper;
	
	@Test
	public void findByParent() {
		List<Category> categories=mapper.findByParent(1L);
		for (Category goodsCategory : categories) {
			System.out.println(goodsCategory);
		}
	}
}
