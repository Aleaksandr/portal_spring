package com.portal.dao;


import com.portal.pojos.User;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for UserDao
 */

public interface IUserDao extends Dao<User>{

    /** Gets the the appropriate User by email parametr value */
    User getUserByEmail(String email);
}