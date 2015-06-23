package com.portal.services.simple;

import com.mysql.jdbc.StringUtils;
import com.portal.dao.IUserDao;
import com.portal.exception.PersistException;
import com.portal.pojos.News;
import com.portal.pojos.User;
import com.portal.services.UserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of User
 */
@Service("userManager")
@Transactional(propagation = Propagation.REQUIRED)
public class SimpleUserManager implements UserManager {

	private static Logger logger = Logger.getLogger(SimpleUserManager.class);

	@Autowired
	IUserDao userDao;

	@Override
	public User getUserByEmail(String email) {
		User us = null;
		if (!(StringUtils.isNullOrEmpty(email))) {
				us = userDao.getUserByEmail(email);
		}
		return us;
	}

	@Override
	public User getUser(String login) {
		User user = new User();

		if (!(StringUtils.isNullOrEmpty(login))) {
			user = userDao.getUserByEmail(login);
		}

		return user;
	}

	@Override
	public void add(User user) throws PersistException {
		userDao.save(user);
	}

	@Override
	public void update(User user) throws PersistException {
		userDao.update(user);
	}

	@Override
	public User get(Serializable id) throws PersistException {
		return userDao.get(id);
	}

	@Override
	public void delete(User user) throws PersistException {
		userDao.remove(user);
	}

	@Override
	public void refresh(User user) throws PersistException {
		userDao.refresh(user);
	}

	@Override
	public List<User> loadAll() throws PersistException {
		return userDao.loadAll();
	}
}