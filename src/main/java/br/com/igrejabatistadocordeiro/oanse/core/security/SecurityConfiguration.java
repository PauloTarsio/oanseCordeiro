package br.com.igrejabatistadocordeiro.oanse.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	private final CustomAuthenticationProvider customAuthenticationProvider;

	public SecurityConfiguration(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic(httpBasic -> httpBasic.init(http)) // Ideal desativar para produção !!!
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // libera estáticos
					.requestMatchers("/login").permitAll() // login sem estar autenticado
					.anyRequest().authenticated())  // resto precisa logar
			.formLogin(form -> form.loginPage("/login") // página customizada
			.loginProcessingUrl("/login") // action do formulário (POST)
			.failureUrl("/login?error=true") // se falhar volta para login com erro
			.defaultSuccessUrl("/", true) // após sucesso vai para /
			.permitAll())
			.logout(logout -> logout.logoutUrl("/logout") // URL de logout
				.logoutSuccessUrl("/login?logout=true") // após logout redireciona
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll())
			.authenticationProvider(customAuthenticationProvider) // seu provider custom
			.csrf(csrf -> csrf.disable()); // (opcional: útil em dev/teste)

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(
				"/v2/api-docs/**",
				"/v3/api-docs/**",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/swagger-ui/**",
				"/webjars/**");
	}

}