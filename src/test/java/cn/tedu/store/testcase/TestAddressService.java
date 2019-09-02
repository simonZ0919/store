package cn.tedu.store.testcase;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAddressService {
	@Autowired
	private IAddressService service;

	@Test
	public void create() {
		Address address=new Address();
		address.setUid(1);
		address.setName("Bob");
		service.create("admin", address);
	}
}
