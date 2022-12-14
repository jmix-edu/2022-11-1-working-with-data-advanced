package com.company.jmixpm.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidProjectDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidProjectDate {

    String message() default "Start date cannot be later than end date";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
