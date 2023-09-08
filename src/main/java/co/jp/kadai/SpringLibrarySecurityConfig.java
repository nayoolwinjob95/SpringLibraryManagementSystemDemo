package co.jp.kadai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SpringLibrarySecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {

		http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/"));

		http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/register", "/login", "/logout", "/resources/**",
				"/book/**", "/category/**", "/search/**").permitAll().anyRequest().authenticated());

		return http.build();
	}

	@Bean
	AuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
