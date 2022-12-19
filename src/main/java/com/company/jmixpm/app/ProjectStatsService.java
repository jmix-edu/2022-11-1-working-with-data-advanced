package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectStats;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectStatsService {

    @Autowired
    private DataManager dataManager;

    public List<ProjectStats> fetchProjectStatistics() {
        List<Project> projects = dataManager.load(Project.class)
                .all()
                .list();

        return projects.stream().map(project -> {
            ProjectStats stats = dataManager.create(ProjectStats.class);
            stats.setId(project.getId());
            stats.setProjectName(project.getName());
            stats.setTasksCount(project.getTasks().size());

            stats.setPlannedEfforts(project.getTotalEstimatedEfforts() == null
                    ? 0 : project.getTotalEstimatedEfforts());

            stats.setActualEfforts(getActualEfforts(project.getId()));

            return stats;
        }).collect(Collectors.toList());
    }

    public Integer getActualEfforts(UUID projectId) {
        return dataManager.loadValue("select sum(te.timeSpent) from TimeEntry " +
                        "te where te.task.project.id = :projectId", Integer.class)
                .parameter("projectId", projectId)
                .one();
    }
}