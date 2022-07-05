package com.prasad.spotify.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prasad.spotify.dau.UserRepository;
import com.prasad.spotify.model.User;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(User user)
    {
        this.userRepository.save(user);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<User> getAllUser()
    {

        return this.userRepository.findAll();
    }

    public User getByUsername(String username)
    {

        return this.userRepository.findByUsername(username);
    }
}
