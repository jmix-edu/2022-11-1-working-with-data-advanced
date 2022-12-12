package com.company.jmixpm.screen.employeedetails;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.EmployeeDetails;

@UiController("EmployeeDetails.edit")
@UiDescriptor("employee-details-edit.xml")
@EditedEntityContainer("employeeDetailsDc")
public class EmployeeDetailsEdit extends StandardEditor<EmployeeDetails> {
}