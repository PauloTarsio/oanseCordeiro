package br.com.igrejabatistadocordeiro.oanse.core.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = @Info(
			title = "Oanse API",
			version = "v001"
		)
)
public class OpenApiConfiguration {

}
