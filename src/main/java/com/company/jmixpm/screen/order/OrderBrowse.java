package com.company.jmixpm.screen.order;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Order;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
}