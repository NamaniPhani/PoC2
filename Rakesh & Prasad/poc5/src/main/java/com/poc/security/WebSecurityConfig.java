//package com.poc.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private AccessDeniedHandler accessDeniedHandler;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().exceptionHandling()
//				.accessDeniedHandler(accessDeniedHandler).and() // added
//				.httpBasic();
//	}
//
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").build();
//		return new InMemoryUserDetailsManager(user);
//	}
//}