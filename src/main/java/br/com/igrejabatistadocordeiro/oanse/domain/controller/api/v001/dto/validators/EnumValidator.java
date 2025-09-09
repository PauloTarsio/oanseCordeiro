package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators;

import java.util.Arrays;

import br.com.igrejabatistadocordeiro.oanse.domain.model.TipoDocumentoPessoa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValido, TipoDocumentoPessoa> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValido annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(TipoDocumentoPessoa value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.equals(value));
    }
}