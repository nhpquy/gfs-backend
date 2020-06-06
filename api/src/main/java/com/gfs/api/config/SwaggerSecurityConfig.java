package com.gfs.api.config;

import com.gfs.domain.component.ScryptKdfPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Order(1)
public class SwaggerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ScryptKdfPasswordEncoder scryptKdfPasswordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withUsername("admin")
                        .password(scryptKdfPasswordEncoder.encode("Gfs@116112"))
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/swagger-ui.html/**")
                .antMatcher("/webjars/**")
                .antMatcher("/v2/api-docs/**")
                .antMatcher("/swagger-resources/**")
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .hasAnyRole("ADMIN", "DEV")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(scryptKdfPasswordEncoder);
        auth.authenticationProvider(authenticationProvider);
    }
}
