package com.huuphuoc.webBH.security.config.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityDetailsService {

    UserDetails loadUserByUsernameAndPassword(String username) throws UsernameNotFoundException;
}
