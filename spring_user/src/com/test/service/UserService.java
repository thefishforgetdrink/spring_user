package com.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.dao.IUserDao;
import com.test.exception.UserException;
import com.test.model.User;
import com.test.util.Pager;

@Service("userService")
public class UserService implements IUserService{
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user) {
		User u = userDao.findByUsername(user.getUsername());
		if(null != u) throw new UserException("要添加的用户已经存在");
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User findById(Integer id) {
		return userDao.findById(id);
	}

	public User login(String username, String password) {
		User u = userDao.findByUsername(username);
		if(u == null) throw new UserException("登录用户不存在");
		if(!u.getPassword().equals(password)) throw new UserException("用户密码不正确");
		return u;
	}

	public Pager find(Pager p) {
		return userDao.find(p);
	}

}
