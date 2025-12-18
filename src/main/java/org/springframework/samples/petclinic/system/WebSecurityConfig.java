package org.springframework.samples.petclinic.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/oups")
			.permitAll()
			.requestMatchers("/resources/**", "/webjars/**", "/css/**")
			.permitAll()
			.requestMatchers("/", "/welcome")
			.permitAll()
			.requestMatchers("/login")
			.permitAll()
			.anyRequest()
			.authenticated())
			.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
			.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
