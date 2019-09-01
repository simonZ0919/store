package cn.tedu.store.testcase;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDB {
	@Autowired
	DataSource dataSource;
	@Autowired
	UserMapper mapper;
	
	@Test
	public void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void addnew() {
        Date now = new Date();
        User user = new User();
        user.setUsername("root");
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
        Integer rows = mapper.addnew(user);
        System.err.println("rows=" + rows);
	}
	
	@Test
	public void findByUserName() {
		System.err.println(mapper.findByUsername("root"));
	}
	
	@Test
	public void findByID() {
		System.err.println(mapper.findById(1));
	}
	
	@Test
	public void updatePassword() {
		Integer rows=mapper.updatePassword(
				4, "123", "root", new Date());
		System.err.println(rows);
	}
	
	@Test
	public void updateAvatar() {
		Integer rows=mapper.updateAvatar(2, "/a.jpg", "spring", new Date());
		System.err.println(rows);
	}
}
