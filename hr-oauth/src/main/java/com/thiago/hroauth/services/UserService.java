package com.thiago.hroauth.services;

import com.thiago.hroauth.entities.User;
import com.thiago.hroauth.feingclients.UserFeingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserFeingClient userFeingClient;

    public User findByEmail(String email){
        User user = userFeingClient.findByEmail(email).getBody();
        if (user == null){
            logger.error("Email not found:" + email);
            throw new IllegalStateException("Email not found");
        }
        logger.info("Email: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeingClient.findByEmail(username).getBody();
        if (user == null){
            logger.error("Email not found:" + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email: " + username);
        return user;
    }
}
