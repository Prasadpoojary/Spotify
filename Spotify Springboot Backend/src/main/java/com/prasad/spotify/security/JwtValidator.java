package com.prasad.spotify.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
//import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtValidator extends OncePerRequestFilter
{
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authenticationHeader = request.getHeader("Authorization");



        if(authenticationHeader==null || !authenticationHeader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        try {
            String key = "futureaimistobecomeasuccessfuldeveloperbypassionandknowledge";
            String token = authenticationHeader.replace("Bearer", "");

            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            Claims body = jwsClaims.getBody();
            String username = body.getSubject();

            List<Map<String,String>>  claimsAuthorities=(List<Map<String,String>>) body.get("authorities");


            Set<GrantedAuthority> authorities = claimsAuthorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());


            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
                    username,null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        } catch (JwtException e)
        {
            throw new IllegalStateException("Login again");
        }


        filterChain.doFilter(request,response);
    }
}