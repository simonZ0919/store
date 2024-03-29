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
public class TestAddressDB {
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
	
	@Test 
	public void findById() {
		System.err.println(mapper.findByUid(7));
	}
	
	@Test
	public void findLastModified() {
		System.err.println(mapper.findLastModified(1));
	}

	@Test 
	public void deleteById() {
		System.err.println(mapper.deleteById(5));
	}
}
