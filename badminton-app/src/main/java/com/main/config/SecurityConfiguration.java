package com.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfiguration {

//		@Bean
//		PasswordEncoder passwordEncoder() {
//			return new BCryptPasswordEncoder(11);
//		}

	private static String[] WHITE_LIST = { "/signup", "/login", "/verificationLink**", "/forgotPassword",
			"/resetPassword","/" };
	private final AuthenticationProvider authenticationProvider;
	private final SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
////			.csrf()
//			.disable()
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST).permitAll().anyRequest().authenticated())
//			.and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		http.authenticationProvider(authenticationProvider);
////			.authenticationProvider(authenticationProvider)
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
