package com.portal.services;


import com.portal.pojos.User;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of UserManager
 */

public interface UserManager extends IService<User> {

    User getUserByEmail(String email);
    User getUser(String login);
}
