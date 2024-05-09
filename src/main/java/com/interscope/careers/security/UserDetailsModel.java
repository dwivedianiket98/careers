package com.interscope.careers.security;

import com.interscope.careers.entity.Roles;
import com.interscope.careers.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDetailsModel implements UserDetails {

    User user;

    UserDetailsModel(User currUser) {
        System.out.println("[*] " + user.getEmail());
        this.user = currUser;
    }

    private String getUserRole() {
        return "ROLE_" + ((user.getRoleId() == Roles.RECRUITER.getRoleId()) ? Roles.RECRUITER.name() : Roles.CANDIDATE.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> userGrantedAuthorities = new ArrayList<>(List.of(new SimpleGrantedAuthority(getUserRole())));
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
