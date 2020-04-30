package com.Divsoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userSecurityService;
	
	private static final  String[] PUBLIC_ENDPOINTS= {
			"/h2-console/**",
			"/Funcionario/**"
			
	};
	
	@Autowired
	private static final  String[] PUBLIC_ENDPOINTS_GET= {
			"/Estoque/**"
	};
	
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//Liberar o H2
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.cors().and().csrf().disable();
		
		httpSecurity.authorizeRequests()
		.antMatchers(PUBLIC_ENDPOINTS).permitAll()
		.antMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS_GET).permitAll()
		.anyRequest().authenticated();
		httpSecurity.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		httpSecurity.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil,userSecurityService));
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
