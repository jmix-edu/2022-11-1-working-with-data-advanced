package com.company.jmixpm.screen.roadmap;

import com.company.jmixpm.entity.Estimation;
import com.company.jmixpm.entity.Roadmap;
import io.jmix.ui.Notifications;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Roadmap.edit")
@UiDescriptor("roadmap-edit.xml")
@EditedEntityContainer("roadmapDc")
public class RoadmapEdit extends StandardEditor<Roadmap> {

    @Autowired
    private Notifications notifications;

    @Subscribe(id = "roadmapDc", target = Target.DATA_CONTAINER)
    public void onRoadmapDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Roadmap> event) {
        notifications.create()
                .withCaption("Roadmap: " + event.getProperty())
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }

    @Subscribe(id = "estimationDc", target = Target.DATA_CONTAINER)
    public void onEstimationDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Estimation> event) {
        notifications.create()
                .withCaption("Estimation: " + event.getProperty())
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }
}