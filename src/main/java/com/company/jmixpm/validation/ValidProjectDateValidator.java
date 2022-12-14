package com.company.jmixpm.validation;

import com.company.jmixpm.entity.Project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ValidProjectDateValidator implements ConstraintValidator<ValidProjectDate, Project> {

    @Override
    public boolean isValid(Project project, ConstraintValidatorContext context) {
        if (project == null) {
            return false;
        }

        LocalDateTime startDate = project.getStartDate();
        LocalDateTime endDate = project.getEndDate();

        if (startDate == null || endDate == null) {
            return true;
        }

        return endDate.compareTo(startDate) > 0;
    }
}
