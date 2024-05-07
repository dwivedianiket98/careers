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
        if (user.getRoleId() == Roles.RECRUITER.getRoleId()) {
            return Roles.RECRUITER.name();
        } else if (user.getRoleId() == Roles.CANDIDATE.getRoleId()) {
            return Roles.CANDIDATE.name();
        } else {
            return null;
        }
    }
}
