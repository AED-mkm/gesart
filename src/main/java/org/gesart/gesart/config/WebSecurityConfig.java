package org.gesart.gesart.config;


import org.gesart.gesart.security.NUserDetailsService;
import org.gesart.gesart.security.jwt.AuthEntryPointJwt;
import org.gesart.gesart.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@SuppressWarnings("ALL")

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class WebSecurityConfig {
    @Autowired
    private NUserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    /**
     * Jwt token filter.
     *
     * @return an instance of token filter
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Config auth manager.
     *
     * @param authConfiguration
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    /**
     * Create password encoder.
     *
     * @return an instance of password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Ignoring some resources from authentication check.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Http security configuration.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/api/users/login",
                            "/api/portail/**",
                            "/api/reset-password/*",
                            "/api/activate-compte",
                            "/api/verif-token-validite",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                });
        return http.build();
    }
}
