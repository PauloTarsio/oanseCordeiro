package br.com.igrejabatistadocordeiro.oanse.domain.validations;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdadeOansistaValidator implements ConstraintValidator<IdadeOansista, LocalDate> {

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
        if (dataNascimento == null) return false;
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        return idade >= 4 && idade <= 14;
    }
}
