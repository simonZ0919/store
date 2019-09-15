package cn.tedu.store.testcase;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.mapper.OrderMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrderDB {
	@Autowired
	OrderMapper mapper;
	
	@Test
	public void findById() {
		System.out.println(mapper.findById(1));
	}
}
