package com.main.service;

import com.main.model.Customer;
import com.main.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    public List<CustomerOrder> getOrders(Customer customer);
}
