package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators;

import java.util.Arrays;

import br.com.igrejabatistadocordeiro.oanse.domain.model.TipoPessoa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValido, TipoPessoa> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValido annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(TipoPessoa value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.equals(value));
    }
}