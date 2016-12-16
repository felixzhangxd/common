package com.firebugsoft.common.jdbc.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.firebugsoft.common.jdbc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class UserServiceTest {
	@Resource
	private UserService userService;
	
	@Test
	public void findById() {
//		userService.saveOrUpdate(new UserPo(1,"felix"));
		System.out.println(userService.findById(1));
	}
}
