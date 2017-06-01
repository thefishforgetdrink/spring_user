package com.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.model.User;
import com.test.util.Pager;

@Repository("userDao")
public class UserDao extends HibernateDaoSupport implements IUserDao{

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	public User findById(Integer id) {
		return this.getHibernateTemplate().load(User.class, id);
	}

	public User findByUsername(String username) {
		return (User)this.getSession().createQuery("from User where username=?")
				.setParameter(0, username).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Pager find(Pager p) {
		Query query = this.getSession().createQuery("from User");
		query.setFirstResult(p.getFirstRow()).setMaxResults(p.getPageRows());
		List list = query.list();
		p.setData(list);
		List list1 = this.getHibernateTemplate().find("from User");
		int total = list1.size();
		p.setTotalRows(total);
		p.setTotalPages(((int)Math.ceil((double)total / (double)p.getPageRows())));
		return p;
	}

}
