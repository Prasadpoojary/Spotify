package com.prasad.spotify.dau;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.spotify.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

    User findByUsername(String username);
}