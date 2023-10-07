package com.main.service;

import com.main.model.Customer;
import com.main.model.CustomerOrder;
import com.main.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public List<CustomerOrder> getOrders(Customer customer) {
        return customerOrderRepository.findByCustomer(customer) ;
    }
}
