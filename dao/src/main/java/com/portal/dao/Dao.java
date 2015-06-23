package com.portal.dao;

import com.portal.exception.PersistException;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

    /** Load a list of all the relevant records in the database */
    public List<T> loadAll() throws PersistException;

    /** It creates a new entry, the corresponding object object */
    public T save(T object) throws PersistException;

    /** Removes the entry of the object from the database */
    public void remove(T t) throws PersistException;

    /** It saves the state of the object group in the database */
    public void update(T object) throws PersistException;

    public void refresh(T t) throws PersistException;

    /** Gets the appropriate record with a primary key or a null key */
    public T get(Serializable id);

}
