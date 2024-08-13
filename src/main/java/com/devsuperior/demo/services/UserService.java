package com.devsuperior.demo.services;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        User user = new User();
        user.setEmail(userOptional.getEmail());
        user.setPassword(userOptional.getPassword());
        user.setId(userOptional.getId());
        userOptional.getRoles().forEach(role -> {
            user.getRoles().add(role);
        });
        return user;
    }
}
