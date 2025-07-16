package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * validador
 * 
 * Esse regex aceita telefones com ou sem DDD, com ou sem hífen. Ex: 11988887777, (11) 98888-7777, 98888-7777.
 * 
 */

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    private static final String REGEX = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // se quiser que @NotBlank trate isso
        }
        return value.matches(REGEX);
    }
}
