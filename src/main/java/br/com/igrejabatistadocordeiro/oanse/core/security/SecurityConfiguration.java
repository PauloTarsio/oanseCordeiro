package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Configura o filtro de segurança HTTP
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
    			.csrf(csrf -> csrf.disable())
    			.httpBasic(Customizer.withDefaults())
    			.formLogin(configurer -> {
    					configurer.loginPage("/login").permitAll();
    				})
    			.authorizeHttpRequests(authorize -> {
    					authorize.anyRequest().authenticated();    				
    				})
    			.build();
    }
}