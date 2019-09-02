package cn.tedu.store.testcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDistrictDB {
	@Autowired
	AddressMapper mapper;
	
	@Test
	public void addnew() {
		Address address=new Address();
		address.setUid(1);
		address.setName("Marry");
		System.err.println(mapper.addNew(address));
	}
	
	@Test 
	public void countAddress() {
		System.err.println(mapper.countAddress(1));
	}
	

}
