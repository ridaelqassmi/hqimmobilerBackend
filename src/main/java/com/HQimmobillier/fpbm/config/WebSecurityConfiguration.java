package com.HQimmobillier.fpbm.config;


import com.HQimmobillier.fpbm.security.CustumeUserDetailImp;
import com.HQimmobillier.fpbm.security.JwtAuthenticationFilter;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustumeUserDetailImp userDetailImp;

    public WebSecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, CustumeUserDetailImp userDetailImp) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailImp = userDetailImp;
    }

    public void configure(WebSecurity web) throws Exception {


        /*web.ignoring().antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/public");*/
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/rent").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll();
        /*this is just during developpment phase*/
        http.authorizeRequests().antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/public").permitAll();
        /*--------end */
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailImp).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public AuthenticationManager authenticationManagerbean() throws Exception {
        return super.authenticationManagerBean();
    }




}
