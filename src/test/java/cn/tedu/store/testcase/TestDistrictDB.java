package cn.tedu.store.testcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DistrictMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDistrictDB {
	@Autowired
	DistrictMapper mapper;
	
	@Test
	public void find() {
		System.out.println(mapper.findByCode("110108"));
		System.out.println(mapper.findByParent("310000"));
	}
	
}
