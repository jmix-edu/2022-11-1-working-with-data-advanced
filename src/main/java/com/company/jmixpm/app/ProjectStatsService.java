package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectStats;
import com.company.jmixpm.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlanRepository;
import io.jmix.core.FetchPlans;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectStatsService {

    private final DataManager dataManager;
    private final FetchPlans fetchPlans;
    private final FetchPlanRepository fetchPlanRepository;

    public ProjectStatsService(DataManager dataManager,
                               FetchPlans fetchPlans,
                               FetchPlanRepository fetchPlanRepository) {
        this.dataManager = dataManager;
        this.fetchPlans = fetchPlans;
        this.fetchPlanRepository = fetchPlanRepository;
    }

    public List<ProjectStats> fetchProjectStatistics() {
        List<Project> projects = dataManager.load(Project.class)
                .all()
                .fetchPlan(fetchPlanRepository.getFetchPlan(Project.class, "project-with-tasks"))
                .list();

        return projects.stream().map(project -> {
            ProjectStats stats = dataManager.create(ProjectStats.class);
            stats.setId(project.getId());
            stats.setProjectName(project.getName());
            stats.setTasksCount(project.getTasks().size());

            Integer estimatedEfforts = project.getTasks().stream()
                    .map(Task::getEstimatedEfforts)
                    .reduce(0, Integer::sum);
            stats.setPlannedEfforts(estimatedEfforts);

            stats.setActualEfforts(getActualEfforts(project.getId()));

            return stats;
        }).collect(Collectors.toList());
    }

    private FetchPlan createFetchPlanWithTasks() {
        return fetchPlans.builder(Project.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .add("tasks", fetchPlanBuilder ->
                        fetchPlanBuilder.add("estimatedEfforts"))
                .build();
    }

    public Integer getActualEfforts(UUID projectId) {
        return dataManager.loadValue("select sum(te.timeSpent) from TimeEntry " +
                        "te where te.task.project.id = :projectId", Integer.class)
                .parameter("projectId", projectId)
                .one();
    }
}