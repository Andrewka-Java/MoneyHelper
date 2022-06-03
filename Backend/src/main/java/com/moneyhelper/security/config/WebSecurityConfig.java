/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.security.config;

import com.moneyhelper.security.JwtAuthenticationEntryPoint;
import com.moneyhelper.security.JwtRequestFilterRefreshToken;
import com.moneyhelper.security.JwtRequestFilterToken;
import com.moneyhelper.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.moneyhelper.util.Constant.BCRYPT_POWER;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilterToken jwtRequestFilterToken;
    private final JwtRequestFilterRefreshToken jwtRequestFilterRefreshToken;
    private final UserDetailsService userDetailsService;

    @Autowired
    WebSecurityConfig(
            final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            final JwtRequestFilterToken jwtRequestFilterToken,
            final JwtRequestFilterRefreshToken jwtRequestFilterRefreshToken,
            final UserServiceImpl userDetailsService
    ) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilterToken = jwtRequestFilterToken;
        this.jwtRequestFilterRefreshToken = jwtRequestFilterRefreshToken;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_POWER);
    }


    /**
     * Issue with StackOverFlow
     * https://stackoverflow.com/questions/42729981/stackoverflowerror-trying-to-expose-authenticationmanager-in-spring-websecurityc
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService);
        super.configure(authenticationManagerBuilder);
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilterToken, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtRequestFilterRefreshToken, UsernamePasswordAuthenticationFilter.class);
    }

}
