package com.zerobeta.contentpublication.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zerobeta.contentpublication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private Integer id;

    private String loginName;

    private Boolean profileCompleted;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Integer id, String loginName, String password, Boolean profileCompleted,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.profileCompleted = profileCompleted;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getUserRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getLoginName(),
                user.getPassword(),
                user.getProfileCompleted(),
                authorities);
    }

    public Integer getId() {
        return id;
    }

    public Boolean getProfileCompleted() {
        return profileCompleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
