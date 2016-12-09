package com.firebugsoft.common.jdbc.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.firebugsoft.common.jdbc.dao.UserDao;
import com.firebugsoft.common.jdbc.po.UserPo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class UserDaoTest {
	@Resource
	private UserDao userDao;
	
	@Test
	public void findById() {
		Integer id=1;
		System.out.println(userDao.findById(id).getName());
//		System.out.println(po.getName());
//		userDao.findById(1);
//		userDao.modifyById(new UserPo(1,"master"));
//		userDao.save(new UserPo(null,"felix"));
//		userDao.removeById(2);
	}
}
