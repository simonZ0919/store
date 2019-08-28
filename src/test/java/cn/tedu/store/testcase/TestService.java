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
			Date now = new Date();
			User user = new User();
			user.setUsername("admin");
			user.setPassword("1234");
			user.setGender(1);
			user.setPhone("13800138001");
			user.setEmail("root@tedu.cn");
			user.setSalt("Hello,MD5!");
			user.setIsDelete(0);
			user.setCreatedUser("Admin");
			user.setModifiedUser("Admin");
			user.setCreatedTime(now);
			user.setModifiedTime(now);
			System.out.println(service.reg(user));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
