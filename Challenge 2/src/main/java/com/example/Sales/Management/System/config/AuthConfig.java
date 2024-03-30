package com.example.Sales.Management.System.config;

import com.example.Sales.Management.System.exception.AuthError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AuthConfig {
//        @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.builder().username("vimal").password(passwordEncoder().encode("14640548")).build();
//        UserDetails user2 = User.builder().username("vivek").password(passwordEncoder().encode("14640548")).build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    @Bean
    public AuthError authError() {
        return new AuthError();
    }

}
