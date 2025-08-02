package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators;

import org.springframework.util.StringUtils;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.DadosPessoaisDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoPessoaValidator implements ConstraintValidator<TipoPessoaValido, DadosPessoaisDTO> {

    @Override
    public boolean isValid(DadosPessoaisDTO dto, ConstraintValidatorContext context) {
        if (dto.tipo() == null) return true; // outra validação (NotNull) cuida disso

        boolean valido = true;
        context.disableDefaultConstraintViolation();

        boolean cpfOk = StringUtils.hasText(dto.cpf());
        boolean rgOk = StringUtils.hasText(dto.rg());
        boolean cnpjOk = StringUtils.hasText(dto.cnpj());
        
        switch (dto.tipo()) {
            case FISICA -> {
                if (!cpfOk && !rgOk) {
                    context.buildConstraintViolationWithTemplate("CPF deve ser informado para pessoa física")
                           .addPropertyNode("cpf").addConstraintViolation();
                    context.buildConstraintViolationWithTemplate("RG deve ser informado para pessoa física")
                           .addPropertyNode("rg").addConstraintViolation();
                    valido = false;
                }
                if (cnpjOk) {
					context.buildConstraintViolationWithTemplate("CNPJ não deve ser informado para pessoa física")
						   .addPropertyNode("cnpj").addConstraintViolation();
					valido = false;
				}
            }
            case JURIDICA -> {
                if (!cnpjOk) {
                    context.buildConstraintViolationWithTemplate("CNPJ deve ser informado para pessoa jurídica")
                           .addPropertyNode("cnpj").addConstraintViolation();
                    valido = false;
                }
                if (cpfOk || rgOk) {
					context.buildConstraintViolationWithTemplate("CPF não deve ser informado para pessoa jurídica")
						   .addPropertyNode("cpf").addConstraintViolation();
					context.buildConstraintViolationWithTemplate("RG não deve ser informado para pessoa jurídica")
						   .addPropertyNode("rg").addConstraintViolation();
					valido = false;
				}
            }
        }

        return valido;
    }
}