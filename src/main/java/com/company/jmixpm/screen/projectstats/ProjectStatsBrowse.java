package com.company.jmixpm.screen.projectstats;

import com.company.jmixpm.app.ProjectStatsService;
import io.jmix.core.LoadContext;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.ProjectStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@UiController("ProjectStats.browse")
@UiDescriptor("project-stats-browse.xml")
@LookupComponent("projectStatsTable")
public class ProjectStatsBrowse extends StandardLookup<ProjectStats> {

    @Autowired
    private ProjectStatsService projectStatsService;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
        return projectStatsService.fetchProjectStatistics();
    }
}