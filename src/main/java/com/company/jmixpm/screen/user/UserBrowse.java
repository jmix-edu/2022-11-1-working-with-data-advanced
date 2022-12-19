package com.company.jmixpm.screen.user;

import com.company.jmixpm.app.UserService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.screen.userprojectsdialog.UserProjectsDialog;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Filter;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
    @Autowired
    private Filter filter;
    @Autowired
    private GroupTable<User> usersTable;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private UserService userService;
    @Autowired
    private ScreenBuilders screenBuilders;

    private Project filterProject;

    @Install(to = "usersDl", target = Target.DATA_LOADER)
    private List<User> usersDlLoadDelegate(LoadContext<User> loadContext) {
        LoadContext.Query query = loadContext.getQuery();
        if (filterProject != null
                && query != null) {
            return userService.getUsersNotInProject(filterProject, query.getFirstResult(), query.getMaxResults());
        } else {
            return dataManager.loadList(loadContext);
        }
    }

    public UserBrowse setFilterProject(Project filterProject) {
        this.filterProject = filterProject;
        filter.setVisible(false);
        return this;
    }

    @Subscribe("usersTable.showUserProjects")
    public void onUsersTableShowUserProjects(Action.ActionPerformedEvent event) {
        User selected = usersTable.getSingleSelected();
        if (selected == null) {
            return;
        }

        screenBuilders.screen(this)
                .withScreenClass(UserProjectsDialog.class)
                .build()
                .withUser(selected)
                .show();
    }
}