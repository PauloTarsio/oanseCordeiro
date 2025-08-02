package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
    // Define o usuário e senha em memória
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("paulo")
            .password(passwordEncoder().encode("987654321")) // Aqui cGF1bG86OTg3NjU0MzIx é o Base64 de paulo:987654321
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Define o encoder da senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) //desabilita para permitir requisições de APIs REST
            	.sessionManagement(session -> session
            			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			).authorizeHttpRequests(auth -> auth
    					.requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
    					.anyRequest().authenticated()
					).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}