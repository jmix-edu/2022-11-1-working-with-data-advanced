package com.company.jmixpm.screen.entitylog;

import com.company.jmixpm.entity.Task;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UiController("ProjectTaskEntityLogBrowse")
@UiDescriptor("project-task-entity-log-browse.xml")
public class ProjectTaskEntityLogBrowse extends Screen {

    @Autowired
    private ComboBox<UUID> projectTasksField;

    @Subscribe(id = "projectTasksDc", target = Target.DATA_CONTAINER)
    public void onProjectTasksDcCollectionChange(CollectionContainer.CollectionChangeEvent<Task> event) {
        List<Task> tasks = event.getSource().getItems();
        projectTasksField.setOptionsMap(tasks.stream()
                .collect(Collectors.toMap(Task::getName, Task::getId)));
    }
}