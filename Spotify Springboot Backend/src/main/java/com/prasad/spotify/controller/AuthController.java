package com.prasad.spotify.controller;

import com.prasad.spotify.model.User;
import com.prasad.spotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class AuthController
{


    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hi hello namasthe...";
    }



    @GetMapping("/all")
    public List<User> allUsers()
    {
        return this.userService.getAllUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        return this.userService.createUser(user);
    }

}
