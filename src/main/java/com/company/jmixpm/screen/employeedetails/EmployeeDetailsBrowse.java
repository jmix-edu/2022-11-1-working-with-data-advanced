package com.company.jmixpm.screen.employeedetails;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.EmployeeDetails;

@UiController("EmployeeDetails.browse")
@UiDescriptor("employee-details-browse.xml")
@LookupComponent("employeeDetailsesTable")
public class EmployeeDetailsBrowse extends StandardLookup<EmployeeDetails> {
}