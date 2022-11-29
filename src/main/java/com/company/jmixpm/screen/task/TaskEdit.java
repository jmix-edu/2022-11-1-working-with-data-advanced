package com.company.jmixpm.screen.task;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Task;

@UiController("Task_.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
public class TaskEdit extends StandardEditor<Task> {
}