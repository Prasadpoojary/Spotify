package com.prasad.spotify.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter
{

    private final AuthenticationManager authenticationManager;

    public UsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            UsernameAndPasswordRequest authRequest=new ObjectMapper().readValue(request.getInputStream(),UsernameAndPasswordRequest.class);

            Authentication authentication=new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key="futureaimistobecomeasuccessfuldeveloperbypassionandknowledge";
        int millis=1000*60*60*24*10;

        String token= Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuedAt(new Date())
                .claim("authorities",authResult.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis()+millis))
                .signWith(SignatureAlgorithm.HS512,key)

                .compact();



        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(
                "{\"status\":\"true\",\"token\":\""+token+"\",\"username\":\""+authResult.getName()+"\"}"
        );
    }
}