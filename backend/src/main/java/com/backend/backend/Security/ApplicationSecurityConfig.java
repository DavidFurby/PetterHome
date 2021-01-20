package com.backend.backend.Security;

import static com.backend.backend.Security.ApplicationUserPermission.USER_PET;
import static com.backend.backend.Security.ApplicationUserRole.ADMIN;
import static com.backend.backend.Security.ApplicationUserRole.ADMINTRAINEE;
import static com.backend.backend.Security.ApplicationUserRole.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(USER.name()).antMatchers(DELETE, "/management/api/**")
                .hasAuthority(USER_PET.getPermission()).antMatchers(POST, "/management/api/**").hasAuthority(USER_PET.getPermission())
                .antMatchers(PUT, "/management/api/**").hasAuthority(USER_PET.getPermission()).antMatchers("/management/api/**")
                .hasAnyRole(ADMIN.name(), ADMINTRAINEE.name()).anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder().username("anna").password(passwordEncoder.encode("password")).authorities(USER.getGrantedAuthority()).build();
                //.roles(USER.name()).build();

        UserDetails lindaUser = User.builder().username("linda").password(passwordEncoder.encode("password123")).authorities(ADMIN.getGrantedAuthority()).build();
                //.roles(ADMIN.name()).build();

        UserDetails tomUser = User.builder().username("tom").password(passwordEncoder.encode("password123")).authorities(ADMINTRAINEE.getGrantedAuthority()).build();  
                //.roles(ADMINTRAINEE.name()).build();

        return new InMemoryUserDetailsManager(annaSmithUser, lindaUser, tomUser);
    }
}
