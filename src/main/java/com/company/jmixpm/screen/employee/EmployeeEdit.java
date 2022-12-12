package com.company.jmixpm.screen.employee;

import com.company.jmixpm.entity.EmployeeDetails;
import io.jmix.core.DataManager;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
public class EmployeeEdit extends StandardEditor<Employee> {

    @Autowired
    private DataContext dataContext;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Employee> event) {
        EmployeeDetails employeeDetails = dataContext.create(EmployeeDetails.class);
        event.getEntity().setEmployeeDetails(employeeDetails);
    }
}