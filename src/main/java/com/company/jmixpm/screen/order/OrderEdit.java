package com.company.jmixpm.screen.order;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Order;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
}