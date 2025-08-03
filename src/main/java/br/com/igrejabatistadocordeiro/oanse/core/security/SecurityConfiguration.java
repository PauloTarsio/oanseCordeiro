package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.igrejabatistadocordeiro.oanse.domain.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
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
					configurer.loginPage("/login");
				})
    			.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/login/**").permitAll();
					authorize.requestMatchers("/api/v001/usuario/**").permitAll();
					
					authorize.anyRequest().authenticated();
				})
    			.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return web -> web.ignoring().requestMatchers(
						"/v2/api-docs/**",
						"/v3/api-docs/**",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/swagger-ui/**",
						"/webjars/**"
					);
    }
    
    @Bean
    public UserDetailsService userDetailService(UsuarioService usuarioService) {
    	return new CustomUserDetailService(usuarioService);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
    
}