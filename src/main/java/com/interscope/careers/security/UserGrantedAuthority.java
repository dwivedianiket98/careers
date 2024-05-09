package com.interscope.careers.security;

import com.interscope.careers.entity.Roles;
import com.interscope.careers.entity.User;
import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {

    User user;

    UserGrantedAuthority(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        System.out.println("ROLE_" + Roles.RECRUITER.name());
        if (user.getRoleId() == Roles.RECRUITER.getRoleId()) {
            return "ROLE_" + Roles.RECRUITER.name();
        } else if (user.getRoleId() == Roles.CANDIDATE.getRoleId()) {
            return "ROLE_" + Roles.CANDIDATE.name();
        } else {
            return null;
        }
    }
}
