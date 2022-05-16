package com.example.demo.inbound.security.filter;
import com.example.demo.inbound.security.JwtUtil;
import com.example.demo.model.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import  org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@AllArgsConstructor
@Component
public class Filter extends OncePerRequestFilter{
private AppUserService appUserService;
private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authorzitaionHeader=request.getHeader("Authorization");
    String email= null;
    String jwt=null;
    if(authorzitaionHeader!=null&&authorzitaionHeader.startsWith("Bearer ")){
        jwt= authorzitaionHeader.substring(7);
        email=jwtUtil.extractUsername(jwt);
    }
    if(email!= null&& SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails=this.appUserService.loadUserByUsername(email);
        if(jwtUtil.validateToken(jwt, userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                 userDetails,null,userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
    }
        filterChain.doFilter(request,response);
    }
}
