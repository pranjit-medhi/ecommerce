package com.main.controller.order;

import com.main.model.Customer;
import com.main.model.CustomerOrder;
import com.main.service.CustomerOrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping("/")
    public List<CustomerOrder> getOrders(@AuthenticationPrincipal Customer customer)
    {
      return customerOrderService.getOrders(customer);
    }
}
