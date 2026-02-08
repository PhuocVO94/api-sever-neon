package com.huuphuoc.api.security.service;

import com.huuphuoc.api.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private  final IUserRepository iUserRepository;
    private  final PasswordEndcoder passwordEndcoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomerUserDetails(user);
    }


}
