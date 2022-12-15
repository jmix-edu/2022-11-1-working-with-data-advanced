package com.company.jmixpm.security;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.Task;
import com.company.jmixpm.entity.TimeEntry;
import com.company.jmixpm.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Developer", code = "developer")
public interface DeveloperRole {
    @EntityAttributePolicy(entityClass = Project.class, attributes = "tasks", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Project.class, attributes = {"id", "name", "startDate", "endDate", "manager", "status", "participants", "totalEstimatedEfforts", "projectLabels", "deletedBy", "deletedDate", "ownerId", "owner"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @MenuPolicy(menuIds = {"Task_.browse", "Project.browse", "TimeEntry.browse", "User.browse"})
    @ScreenPolicy(screenIds = {"Task_.browse", "Project.browse", "TimeEntry.browse", "User.browse", "Project.edit", "Task_.edit", "User.edit", "TimeEntry.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = TimeEntry.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TimeEntry.class, actions = EntityPolicyAction.ALL)
    void timeEntry();
}