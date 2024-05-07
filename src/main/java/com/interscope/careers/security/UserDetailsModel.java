package com.interscope.careers.security;

import com.interscope.careers.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsModel implements UserDetails {

    User user;

    UserDetailsModel(User currUser) {
        this.user = currUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<UserGrantedAuthority> userGrantedAuthorities = new ArrayList<>(List.of(new UserGrantedAuthority(user)));
        return userGrantedAuthorities;
    }

    // enable encryption and decryption here for passwords
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    // need to create db entries for following values
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
