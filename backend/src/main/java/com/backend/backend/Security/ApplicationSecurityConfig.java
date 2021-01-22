package com.backend.backend.Security;

import static com.backend.backend.Security.ApplicationUserPermission.USER_PET;
import static com.backend.backend.Security.ApplicationUserRole.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import com.backend.backend.Auth.ApplicationUserService;
import com.backend.backend.Jwt.JwtTokenVerifier;
import com.backend.backend.Jwt.JwtUsernameAndPasswordAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http// .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(
                        new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(),
                        JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll().antMatchers("/api/**")
                .hasRole(USER.name())/*
                                      * .antMatchers(DELETE, "/management/api/**")
                                      * .hasAuthority(USER_PET.getPermission()).antMatchers(POST,
                                      * "/management/api/**")
                                      * .hasAuthority(USER_PET.getPermission()).antMatchers(PUT,
                                      * "/management/api/**")
                                      * .hasAuthority(USER_PET.getPermission()).antMatchers("/management/api/**")
                                      * .hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                                      */.anyRequest()
                .authenticated();/*
                                  * .formLogin().loginPage("/login").permitAll() .defaultSuccessUrl("/courses",
                                  * true).passwordParameter("password").usernameParameter("username").and()
                                  * .rememberMe().tokenValiditySeconds((int)
                                  * TimeUnit.DAYS.toSeconds(21)).key("somethingverysecured")
                                  * .rememberMeParameter("remember-me").and().logout().logoutUrl("/logout")
                                  * .logoutRequestMatcher(new AntPathRequestMatcher("/logout",
                                  * "GET")).clearAuthentication(true)
                                  * .invalidateHttpSession(true).deleteCookies("JSESSIONID",
                                  * "remember-me").logoutSuccessUrl("/login"); // defaults // to // 2 // weeks
                                  */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
    /*
     * @Override
     * 
     * @Bean protected UserDetailsService userDetailsService() { UserDetails
     * annaSmithUser =
     * User.builder().username("anna").password(passwordEncoder.encode("password"))
     * .authorities(USER.getGrantedAuthority()).build(); //
     * .roles(USER.name()).build();
     * 
     * UserDetails lindaUser =
     * User.builder().username("linda").password(passwordEncoder.encode(
     * "password123")) .authorities(ADMIN.getGrantedAuthority()).build(); //
     * .roles(ADMIN.name()).build();
     * 
     * UserDetails tomUser =
     * User.builder().username("tom").password(passwordEncoder.encode("password123")
     * ) .authorities(ADMINTRAINEE.getGrantedAuthority()).build(); //
     * .roles(ADMINTRAINEE.name()).build();
     * 
     * return new InMemoryUserDetailsManager(annaSmithUser, lindaUser, tomUser); }
     */
}
