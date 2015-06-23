package com.portal.dao;

import java.util.List;

/**
 * Created by yslabko on 024 24 мар 2015.
 */
public interface IPersonDao <T> extends Dao<T> {
    List<T> getPersons();
}
