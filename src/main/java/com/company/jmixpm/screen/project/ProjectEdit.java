package com.company.jmixpm.screen.project;

import com.company.jmixpm.app.ProjectService;
import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.screen.user.UserBrowse;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Field;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
public class ProjectEdit extends StandardEditor<Project> {

    @Autowired
    private Field<ProjectLabels> projectLabelsField;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Validator validator;

    @Subscribe
    public void onInit(InitEvent event) {
//        setCrossFieldValidate(false);

        Collection<io.jmix.ui.component.validation.Validator<ProjectLabels>> validators
                = projectLabelsField.getValidators();
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Project> event) {
        projectLabelsField.setEditable(true);

        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "enhancement", "task")));
    }

    @Subscribe("commitWithBeanValidation")
    public void onCommitWithBeanValidationClick(Button.ClickEvent event) {
        try {
            projectService.saveProject(getEditedEntity());
            close(new StandardCloseAction(WINDOW_CLOSE, false));
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }

            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption(sb.toString())
                    .show();
        }
    }

    @Subscribe("performBeanValidationBtn")
    public void onPerformBeanValidationBtnClick(Button.ClickEvent event) {
        Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity(),
                Default.class, UiCrossFieldChecks.class, UiComponentChecks.class);

        showBeanValidationExceptions(violations);
    }

    private void showBeanValidationExceptions(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(sb.toString())
                .show();
    }

    @Install(to = "usersTable.add", subject = "screenConfigurer")
    private void usersTableAddScreenConfigurer(Screen screen) {
        ((UserBrowse) screen).setFilterProject(getEditedEntity());
    }
}