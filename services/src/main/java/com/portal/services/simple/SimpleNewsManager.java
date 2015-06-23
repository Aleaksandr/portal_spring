package com.portal.services.simple;

import com.portal.dao.INewsDao;
import com.portal.exception.PersistException;
import com.portal.pojos.News;
import com.portal.services.NewsManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of News
 */

@Service("newsManager")
@Transactional(propagation = Propagation.REQUIRED)
public class SimpleNewsManager implements NewsManager {

	protected static Logger logger = Logger.getLogger(SimpleNewsManager.class);

	@Autowired
	INewsDao newsDao;


	@Override
	public List<News> getNewsByAuthor(String author) {
		List<News> nList = null;
		nList = newsDao.getNewsByAuthor(author);
		return nList;
	}

	@Override
	public List<News> getNewsByDate(Date date) {
		return null;
	}

	@Override
	public News getNewsByTitle(String title) {
		News nw = null;
		nw = newsDao.getNewsByTitle(title);
		return nw;
	}

	@Override
	public List<News> getNewsByPeriod(Integer first, Integer second) throws PersistException {
		List<News> nList = null;
		nList = newsDao.getNewsByPeriod(first, second);
		return nList;
	}

	@Override
	public List<News> getAll() throws PersistException {
		List<News> nList = null;
		nList = newsDao.getAll();
		return nList;
	}


	@Override
	public void add(News news) throws PersistException {
		newsDao.save(news);
	}

	@Override
	public void update(News news) throws PersistException {
		newsDao.update(news);
	}

	@Override
	public News get(Serializable id) throws PersistException {
		return newsDao.get(id);
	}

	@Override
	public void delete(News news) throws PersistException {
		newsDao.remove(news);
	}

	@Override
	public void refresh(News news) throws PersistException {
		newsDao.refresh(news);
	}

	@Override
	public List<News> loadAll() throws PersistException {
		return newsDao.loadAll();
	}
}
