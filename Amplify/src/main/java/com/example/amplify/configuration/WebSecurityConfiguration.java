package com.example.amplify.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/inicio").permitAll();

        http.authorizeRequests().antMatchers("/login").permitAll();

        http.authorizeRequests().antMatchers("/album/{albumName}").permitAll();
        http.authorizeRequests().antMatchers("/artista/{artistName}").permitAll();

        //Private pages
        //http.authorizeRequests().anyRequest().authenticated();

        //Login
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/login");

        //Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
