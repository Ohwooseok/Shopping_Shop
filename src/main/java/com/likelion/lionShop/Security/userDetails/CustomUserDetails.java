package com.likelion.lionShop.Security.userDetails;


import com.likelion.lionShop.User.Entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final boolean isStaff;

    public CustomUserDetails(String email, String password, boolean isStaff) {
        this.email = email;
        this.password = password;
        this.isStaff = isStaff;
    }

    public boolean getStaff() {return isStaff;}

    // 해당 User 의 권한을 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (isStaff) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Account 가 만료되었는지?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Account 가 잠겨있는지?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Credential 만료되지 않았는지?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화가 되어있는지?
    @Override
    public boolean isEnabled() {
        // User Entity 에서 Status 가져온 후 true? false? 검사
        return true;
    }
}