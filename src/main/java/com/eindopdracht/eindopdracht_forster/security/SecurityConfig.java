package com.eindopdracht.eindopdracht_forster.security;

import com.eindopdracht.eindopdracht_forster.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(udService);
        return new ProviderManager(auth);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()

                        .requestMatchers(HttpMethod.GET, "/customers").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.POST, "/customers").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.DELETE, "/customers").hasRole("ADMINISTRATION")

                        .requestMatchers(HttpMethod.POST, "/cars").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.GET,"/cars/{id}").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.DELETE, "/cars/{id}").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.POST, "/cars/inspectiondate/{registration}/{inspectionDate}").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST, "/cars/repairdate/{registration}/{repairDate}").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST, "/cars/assigncustomer/{customerId}/{carId}").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.POST, "/cars/neededrepairs/{type}/{registration}").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST, "/cars/donerepairs/{type}/{registration}").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST, "/cars/usedparts/{type}/{registration}").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST,"/cars/{carId}/repair").hasRole("MECHANIC")
                        .requestMatchers(HttpMethod.POST, "/cars/{registration}/papers").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.GET, "/cars/{registration}/papers").hasRole("ADMINISTRATION")

                        .requestMatchers(HttpMethod.POST,"/repairs").hasRole("SUPPLY")
                        .requestMatchers(HttpMethod.DELETE,"/repairs/{id}").hasRole("SUPPLY")
                        .requestMatchers(HttpMethod.GET, "/repairs").hasAnyRole("SUPPLY")

                        .requestMatchers(HttpMethod.POST, "/parts").hasRole("SUPPLY")
                        .requestMatchers(HttpMethod.DELETE,"/parts/{type}").hasRole("SUPPLY")
                        .requestMatchers(HttpMethod.GET, "/parts").hasAnyRole("SUPPLY", "ADMINISTRATION","MECHANIC")
                        .requestMatchers(HttpMethod.PUT, "/parts/{type}").hasRole("SUPPLY")

                        .requestMatchers(HttpMethod.POST, "/invoices/create").hasRole("ADMINISTRATION")
                        .requestMatchers(HttpMethod.POST, "/invoices/paid/{id}").hasRole("ADMINISTRATION")
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
