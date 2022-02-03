package com.zerobeta.contentpublication.security;

import com.zerobeta.contentpublication.entity.User;
import com.zerobeta.contentpublication.respository.UserRepository;
import com.zerobeta.contentpublication.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLoginName(username);
        if(user != null){
            return CustomUserDetails.build(user);
        } else {
            throw new UsernameNotFoundException("User Name not found");
        }

    }
}
