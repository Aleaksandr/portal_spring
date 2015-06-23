package com.portal.impl;


import com.portal.dao.BaseDao;
import com.portal.dao.ICommentDao;
import com.portal.pojos.Comment;
import com.portal.pojos.News;
import com.portal.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with Comment bean and hibernate
 */

@Repository("commentDao")
public class CommentDaoImpl extends BaseDao<Comment> implements ICommentDao {

    private static Logger logger = Logger.getLogger(CommentDaoImpl.class);

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    /** Gets the the appropriate List<Comment> by user parameter value */
    @Override
    public List<Comment> getCommentByUser(User user) {
        List<Comment> commList = null;
        Session session = getSession();
        Criteria criteria = session.createCriteria(Comment.class);
        criteria.setCacheable(true);
        commList = criteria.add(Restrictions.eq("news_id", user.getId())).list();
        return commList;

    }

    /** Gets the the appropriate List<Comment> by News object */
    @Override
    public List<Comment> getCommentByItem(News nw){
        List<Comment> commList = null;
        Session session = getSession();
        commList = nw.getComments();
        return commList;
    }
}
