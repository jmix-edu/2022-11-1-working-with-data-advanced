package com.company.jmixpm.screen.customersearchscreen2;

import com.company.jmixpm.entity.Customer;
import com.google.common.base.Strings;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("CustomerSearchScreen2")
@UiDescriptor("customer-search-screen2.xml")
public class CustomerSearchScreen2 extends Screen {
    @Autowired
    private CollectionLoader<Customer> customersDl;

    @Subscribe("nameField")
    public void onNameFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        customersDl.setParameter("name",
                Strings.isNullOrEmpty(event.getValue())
                        ? null
                        : "(?i)%" + event.getValue() + "%");
//                        : event.getValue());

        customersDl.load();
    }
}