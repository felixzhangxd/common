package com.firebugsoft.common.jdbc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.firebugsoft.common.jdbc.dao.UserDao;
import com.firebugsoft.common.jdbc.po.UserPo;

@Service
public class UserService {
	@Resource
	private UserService userService;
	@Resource
	private UserDao userDao;
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=true, propagation=Propagation.REQUIRED)
	public UserPo findById(Integer id) {
		return userDao.findById(id);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=false, propagation=Propagation.REQUIRED)
	public void modifyById(UserPo po) {
		userDao.modifyById(po);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=false, propagation=Propagation.REQUIRED)
	public void save(UserPo po) {
		userDao.save(po);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=false, propagation=Propagation.REQUIRED)
	public void saveOrUpdate(UserPo user) {
		UserPo po = userService.findById(user.getId());
		if(po == null) {
		    userService.save(po);
		} else {
		    userService.modifyById(po);
		}
	}
}
