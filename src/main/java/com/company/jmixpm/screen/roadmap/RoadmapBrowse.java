package com.company.jmixpm.screen.roadmap;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Roadmap;

@UiController("Roadmap.browse")
@UiDescriptor("roadmap-browse.xml")
@LookupComponent("roadmapsTable")
public class RoadmapBrowse extends StandardLookup<Roadmap> {
}