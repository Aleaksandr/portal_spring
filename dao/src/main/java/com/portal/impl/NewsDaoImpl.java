package com.portal.impl;


import com.portal.dao.BaseDao;
import com.portal.dao.INewsDao;
import com.portal.exception.PersistException;
import com.portal.pojos.News;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with News bean and mysql database
 */

@Repository("newsDao")
public class NewsDaoImpl extends BaseDao<News> implements INewsDao {

    private static Logger logger = Logger.getLogger(NewsDaoImpl.class);

    @Autowired
    public NewsDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }



    /** Gets the the appropriate List<News> by author value parameter */
    @Override
    public List<News> getNewsByAuthor(String author) {
        List<News> t = null;
            Session session = getSession();
            Criteria criteria = session.createCriteria(News.class);
            criteria.setCacheable(true);
            t = (List<News>) criteria.add(Restrictions.eq("author", author)).list();
        return (List<News>) t;
    }

    /** Gets the the appropriate News by title value parameter */
    @Override
    public News getNewsByTitle(String title) {
        News t = null;
            Session session = getSession();
            Criteria criteria = session.createCriteria(News.class);
            criteria.setCacheable(true);
            t = (News) criteria.add(Restrictions.eq("title", title)).list().get(1);

        return t;
    }

    /** Gets the the appropriate List<News> by period */
    @Override
    public List<News> getNewsByPeriod(Integer first, Integer second) throws PersistException {
        List<News> newses;
        try {
            String hql = "FROM News";
            Integer pageSize = second - first;
            Query query = getSession().createQuery(hql)
                    .setFirstResult(first)
                    .setMaxResults(pageSize);
            newses = query.list();
        } catch (HibernateException e) {
            throw new PersistException(e);
        }
        return newses;
    }

    public List<News> getAll() throws PersistException {
        List<News> newses;
        try {
            String hql = "FROM News";
            Query query = getSession().createQuery(hql);
            newses = query.list();
        } catch (HibernateException e) {
            throw new PersistException(e);
        }
        return newses;
    }

    /** Gets the the appropriate List<Comment> by date value parameter */
    @Override
    public List<News> getNewsByDate(Date date) {
        return null;
    }


}
