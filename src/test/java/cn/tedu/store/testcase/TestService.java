package cn.tedu.store.testcase;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
	@Autowired
	private IUserService service;

	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("spring");
			user.setPassword("1234");
			user.setGender(1);
			user.setPhone("13800138001");
			user.setEmail("spring@tedu.cn");
			System.out.println(service.reg(user));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void changeInfo() {
		try {
			User user = new User();
			user.setId(5);
			user.setGender(1);
			user.setPhone("13800138001");
			user.setEmail("spring@tedu.cn");
			service.changeInfo(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void changeAvatar() {
		try {
			service.changeAvatar(2, "abc/test.jpg");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void getById() {
		try {
			User user=service.getById(1);
			System.err.println(user.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
