package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TipoPessoaValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoPessoaValido {
    String message() default "Dados inconsistentes com o tipo de pessoa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

