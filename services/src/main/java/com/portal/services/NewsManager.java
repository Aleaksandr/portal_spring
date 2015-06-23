package com.portal.services;


import com.portal.exception.PersistException;
import com.portal.pojos.News;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of NewsManager
 */

public interface NewsManager extends IService<News> {

	/** Gets the the appropriate List<News> by author value parameter */
	List<News> getNewsByAuthor(String author);

	/** Gets the the appropriate List<News> by date value parameter */
	List<News> getNewsByDate(Date date);

	/** Gets the the appropriate News by title value parameter */
	News getNewsByTitle(String title);

	/** Gets the the appropriate List<News> by period */
	public List<News> getNewsByPeriod(Integer first, Integer second) throws PersistException;

	public List<News> getAll() throws PersistException;
}
