package com.firebugsoft.common.jdbc.dao;

import com.hxzxg.common.bean.enums.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class UserDaoTest {
    @Resource
    private RoleDao roleDao;

    @Test
    public void save() throws IllegalAccessException {
        System.out.println(Gender.F);
//        Role r = new Role();
//        r.setId(4);
//        r.setUsername("username");
//        r.setPassword("password");
//        r.setName("name");
//        r.setGender(Gender.F);
//        r.setMobile("mobile");
//        r.setCreateAt(new Timestamp(System.currentTimeMillis()));
//        r.setUpdateAt(new Timestamp(System.currentTimeMillis()));
//        roleDao.save(r);
    }

}