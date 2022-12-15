package com.company.jmixpm.screen.customer;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Customer;

@UiController("Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}