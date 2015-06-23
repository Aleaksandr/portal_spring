/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 020 20.06.2014
 */
package com.portal.dao;

import com.portal.exception.PersistException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class BaseDao<T> implements Dao<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T save(T t) {
        getSession().save(t);
        log.info("Save:" + t);
        return (T) t;
    }

    @Override
    public void update(T t) {
        getSession().update(t);
        log.info("Update:" + t);
    }

    @Override
    public T get(Serializable id) {
        log.info("Get:" + id);
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public void remove(T t) throws PersistException {
        try {
            getSession().delete(t);
            log.info("Delete: " + t);
        } catch (HibernateException e) {
            log.error("Error delete object from Database: " + e);
            throw new PersistException(e);
        }
    }

    @Override
    public void refresh(T t) throws PersistException {
        try {
            log.info("Refresh:" + t);
            getSession().refresh(t);
        } catch (HibernateException e) {
            log.error("Error delete object from Database: " + e);
            throw new PersistException(e);
        }
    }

    public List<T> loadAll() {
        List<T> tAll;

        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.setCacheable(true);
        tAll = criteria.list();

        return tAll;
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
