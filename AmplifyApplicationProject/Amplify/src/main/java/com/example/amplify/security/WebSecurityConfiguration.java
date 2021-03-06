package com.example.amplify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/inicio").permitAll();

        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/successLogout").permitAll();

        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/registerUser").permitAll();

        http.authorizeRequests().antMatchers("/errorLogin").permitAll();
        http.authorizeRequests().antMatchers("/errorRegister").permitAll();

        http.authorizeRequests().antMatchers("/biblioteca/{username}/**").permitAll();
        http.authorizeRequests().antMatchers("/album/**").permitAll();
        http.authorizeRequests().antMatchers("/artista/**").permitAll();
        http.authorizeRequests().antMatchers("/playlist/**").permitAll();


        //Private pages

        //Admin pages
        http.authorizeRequests().antMatchers("/statisticsMail").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/recommendationsMail").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/redirect/email/estadisticas").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/redirect/email/recomendaciones").hasAnyRole("ADMIN");

        //User pages
        http.authorizeRequests().anyRequest().hasAnyRole("USER");


        //Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/errorLogin");

        //Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/successLogout");
    }

    @Override
    public void configure(WebSecurity wb) throws Exception {
        wb.ignoring().antMatchers("/resources/**", "/static/**", "/css/**");
    }
}
