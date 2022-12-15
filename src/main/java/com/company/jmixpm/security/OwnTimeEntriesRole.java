package com.company.jmixpm.security;

import com.company.jmixpm.entity.TimeEntry;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "own-time-entries", code = "own-time-entries")
public interface OwnTimeEntriesRole {

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntry();
}