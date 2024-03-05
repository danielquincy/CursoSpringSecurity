package com.example.CursoSpringSecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.CursoSpringSecurity.document.UserDocument;
import com.example.CursoSpringSecurity.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDocument> finduser = userRepository.findByUsername(username);
        if(!finduser.isPresent()){
            throw new UsernameNotFoundException(username + " not found");
        }
        return finduser.get();
    }

}
