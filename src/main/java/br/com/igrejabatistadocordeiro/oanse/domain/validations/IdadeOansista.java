package br.com.igrejabatistadocordeiro.oanse.domain.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdadeOansistaValidator.class)
public @interface IdadeOansista {
    String message() default "A idade deve estar entre 4 e 14 anos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
