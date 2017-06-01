package com.test.service;


import com.test.model.User;
import com.test.util.Pager;

public interface IUserService {
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public User findById(Integer id);
	public User login(String username, String password);
	public Pager find(Pager p);
}
