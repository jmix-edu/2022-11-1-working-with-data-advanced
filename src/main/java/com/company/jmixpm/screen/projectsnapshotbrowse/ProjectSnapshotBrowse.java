package com.company.jmixpm.screen.projectsnapshotbrowse;

import com.company.jmixpm.entity.Project;
import io.jmix.auditui.screen.snapshot.SnapshotDiffViewer;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@UiController("ProjectSnapshotBrowse")
@UiDescriptor("project-snapshot-browse.xml")
public class ProjectSnapshotBrowse extends Screen {

    @Autowired
    private SnapshotDiffViewer snapshotDiff;

    @Subscribe("projectsTable")
    public void onProjectsTableSelection(Table.SelectionEvent<Project> event) {
        if (CollectionUtils.isNotEmpty(event.getSelected())) {
            snapshotDiff.loadVersions(event.getSelected().iterator().next());
        }
    }
}