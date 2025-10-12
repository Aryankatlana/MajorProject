package com.kanha.Medium_backend.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //This is our own filter to SB
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                request -> request.requestMatchers("/article/get")
                        .permitAll().anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());

//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    //In memory Database
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("Bhumika")
                .password("{noop}1234")
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("John")
                .password("{noop}1111")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, user2);
    }


}
