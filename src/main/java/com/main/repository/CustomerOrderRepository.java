package com.main.repository;

import com.main.model.Customer;
import com.main.model.CustomerOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomer(Customer customer);
}
