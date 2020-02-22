package com.stone.meSalva.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
//                .and()
//                .withUser("dan").password(passwordEncoder().encode("dan123")).roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/usuarios/**").permitAll()
                .antMatchers(HttpMethod.GET, "/estabelecimentos/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/estabelecimentos/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/estabelecimentos/**").permitAll()
                .antMatchers(HttpMethod.POST, "/estabelecimentos/**").permitAll()
                .antMatchers(HttpMethod.GET, "/emprestimos/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/emprestimos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/emprestimos/**").permitAll()
                .antMatchers(HttpMethod.POST, "/emprestimos/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
