package lk.ijse.gdse.springboot.notetakerV2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // Value annotation is used to inject values from the application.properties file
    @Value("${secure.basic.username}")
    String username;
    @Value("${secure.basic.password}")
    String password;
    @Value("${secure.basic.role}")
    String role;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    // Basic Authentication : this is the most basic form of authentication and it is not recommended for production use
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails principleUser = User.withDefaultPasswordEncoder()
//                .username("Charaka")
//                .password("Charaka123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(principleUser);
//    }

    // Basic Authentication : this is the most basic form of authentication and it is not recommended for production use
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails principleUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role)
                .build();
        return new InMemoryUserDetailsManager(principleUser);
    }
}
