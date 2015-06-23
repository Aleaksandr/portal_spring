package com.portal.impl;

import com.portal.dao.BaseDao;
import com.portal.dao.IUserDao;
import com.portal.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class to work with User bean and mysql database
 */

@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    /** Gets the the appropriate User by email parametr value */
    @Override
    public User getUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        User result = null;
        String hql = "SELECT u FROM User u WHERE u.email = :targetemail";
            Query query = getSession().createQuery(hql);
            query.setParameter("targetemail", email);
            query.setCacheable(true);
            result = (User)query.uniqueResult();
            logger.info("Get user by email: " + result);

        if (result == null) {
            logger.info("getUserByEmail: is Empty ");
            return null;
        }
        return result;
    }
}
