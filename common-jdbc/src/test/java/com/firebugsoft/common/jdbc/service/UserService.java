package com.firebugsoft.common.jdbc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.firebugsoft.common.jdbc.dao.UserDao;
import com.firebugsoft.common.jdbc.po.UserPo;

@Service
public class UserService {
	private UserDao userDao;
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=false, propagation=Propagation.REQUIRED)
	public void saveOrUpdate(UserPo user) {
		UserPo po = userDao.findById(user.getId());
		if(po == null) {
			userDao.save(po);
		}else {
			userDao.modifyById(user);
		}
	}
}
