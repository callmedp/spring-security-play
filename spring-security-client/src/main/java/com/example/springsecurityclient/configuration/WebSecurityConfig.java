package com.example.springsecurityclient.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(11);
    }

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(
                    auth -> {
                        auth.requestMatchers(
                                "/verify-registration",
                                "/register",
                                "/resend-token",
                                "/reset-password",
                                "/save-password",
                                "/change-password").permitAll();
                        try {
                            auth.requestMatchers("/api/**").authenticated()
                                    .and().oauth2Login(
                                            oauth2login -> oauth2login.loginPage("/oath2/authorization/api-client-oidc")
                                    ).oauth2Client(Customizer.withDefaults());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                );




        return http.build();
   }
}
