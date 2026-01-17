package com.huuphuoc.webBH.security.config.service;

import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("SecurityDetailsService")
public class SecurityDetailsServiceImpl implements  UserDetailsService{

    IUserRepository iUserRepository;
    public SecurityDetailsServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByEmail(username) // Giả sử có phương thức này
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
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
        };

        return userDetails;

    }
}

