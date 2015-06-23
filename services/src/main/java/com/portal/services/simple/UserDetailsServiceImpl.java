package com.portal.services.simple;


import com.portal.pojos.User;
import com.portal.pojos.enums.UserRoleEnum;
import com.portal.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        if (!(email == null)) {
            // с помощью нашего сервиса UserService получаем User
            User user = userService.getUserByEmail(email);
            // указываем роли для этого пользователя
            Set<GrantedAuthority> roles = new HashSet();
            // на основании полученныйх даных формируем объект UserDetails
            // который позволит проверить введеный пользователем логин и пароль
            // и уже потом аутентифицировать пользователя
            System.out.println("---------------------------------------------------------------");
            if (user != null){
                roles.add(new SimpleGrantedAuthority(user.getRole()));
                UserDetails userDetails =
                        new org.springframework.security.core.userdetails.User(user.getEmail(),
                                user.getPass(),
                                roles);
                System.out.println(userDetails.getAuthorities());
                System.out.println(userDetails.getPassword());
                System.out.println(userDetails.getUsername());

                return userDetails;
            } else {
                throw new UsernameNotFoundException("No found user");
            }
        } else return null;
    }

}