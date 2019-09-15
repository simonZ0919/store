package cn.tedu.store.testcase;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Category;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.CategoryMapper;
import cn.tedu.store.mapper.GoodsMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoodsDB {
	@Autowired
	GoodsMapper mapper;
	
	@Test
	public void findByCategory() {
		List<Goods> goods=mapper.findByCategory(163L, 10, 10);
		for (Goods good : goods) {
			System.out.println(good);
		}
	}
	
	@Test
	public void findById() {
		System.out.println(mapper.findById(100000021L));
		List<Goods> goods=mapper.findByPri(5);
		for (Goods good : goods) {
			System.out.println(good);
		}
	}
}
