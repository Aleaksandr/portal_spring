package com.portal.pojos;


import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * User bean
 */

@Entity
@Table(name = "users")

public class User implements Serializable {

    private static final long serialVersionUID = 9128510778543609157L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)

    private UserDetail userDetail;


    public User() {
    }

    public User(String login, String password) {
        this.email = login;
        this.pass = password;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return uid;
    }

    public void setId(Integer id) {
        this.uid = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User person = (User) o;

        if (uid != null ? !uid.equals(person.uid) : person.uid != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (role != null ? !role.equals(person.role) : person.role != null) return false;
        if (pass != null ? !pass.equals(person.pass) : person.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User : id: " + uid + " E-mail: " + email + " Role: " + role;
    }
}
