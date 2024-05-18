package com.mdsl.institutionservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{

	private final UserDetailsServiceImpl userDetailsService;
	private final JwtAuthFilter jwtAuthFilter;
	private final CustomAccessDeniedHandler accessDeniedHandler;
	@Qualifier("customAuthenticationEntryPoint")
	private final AuthenticationEntryPoint authEntryPoint;

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception
	{
		return http.cors(AbstractHttpConfigurer::disable)
				   .csrf(AbstractHttpConfigurer::disable)
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   // Set permissions on endpoints
				   .authorizeHttpRequests(auth -> auth
						   // public endpoints
						   .requestMatchers("/actuator/**")
						   .permitAll()
						   .requestMatchers(HttpMethod.GET, "/swagger-ui/**")
						   .permitAll()
						   .requestMatchers(HttpMethod.GET, "v3/api-docs/**")
						   .permitAll()
						   .requestMatchers(HttpMethod.POST, "/auth/v1/login")
						   .permitAll()
						   .requestMatchers(HttpMethod.POST, "/auth/v1/refreshToken")
						   .permitAll()
						   // private endpoints
						   .requestMatchers(HttpMethod.DELETE, "/institution/v1")
						   .hasRole("ADMIN")
						   .requestMatchers("/**")
						   .hasAnyRole("USER", "ADMIN")
						   .anyRequest()
						   .authenticated())
				   .authenticationManager(authenticationManager)
				   // Handle Unauthorized requests
				   .exceptionHandling(e -> e.accessDeniedHandler(accessDeniedHandler))
				   // Handle Unauthenticated requests
				   .httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint))
				   .exceptionHandling(Customizer.withDefaults())
				   // Add JWT token filter
				   .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception
	{
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}
}
