package com.HQimmobillier.fpbm.security;

import com.HQimmobillier.fpbm.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.resolveToken(request);
        System.out.println(token);

        try{
            if(token !=null && jwtProvider.validateToken(token)){
                System.out.println(" whaaaaaaaaaaaaa");
                Authentication auth =jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (ApiRequestException e){
            SecurityContextHolder.clearContext();
            System.out.println("i am here ");
            res.sendError(HttpStatus.UNAUTHORIZED.value(),"you are not authenticated");
        }
        filterChain.doFilter(request,res);
    }
}
