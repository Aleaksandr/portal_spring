/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 023 23.06.2014
 */
package com.portal.services;

import com.portal.dao.Dao;
import com.portal.exception.PersistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseService<T> implements IService<T> {

    private static Logger logger = Logger.getLogger(BaseService.class);

    private Dao<T> baseDao;

    public BaseService() {
    }

    @Autowired
    public BaseService(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t) throws PersistException {
        baseDao.save(t);
    }

    @Override
    public void update(T t) throws PersistException {
        baseDao.update(t);
    }

    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    public void delete(T t) throws PersistException {
        baseDao.remove(t);
    }

    @Override
    public void refresh(T t) throws PersistException {
        baseDao.refresh(t);
    }

    @Override
    public List<T> loadAll() throws PersistException {
        return baseDao.loadAll();
    }
}
