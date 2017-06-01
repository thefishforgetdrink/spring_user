package com.test.dao;


import com.test.model.User;
import com.test.util.Pager;

public interface IUserDao {
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public User findById(Integer id);
	public User findByUsername(String username);
	public Pager find(Pager p);
}
