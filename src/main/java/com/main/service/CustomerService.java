package com.main.service;

import com.main.dto.CustomerDto;
import com.main.dto.CustomerLoginDto;
import com.main.exception.CustomerExistsException;
import com.main.model.Customer;

public interface CustomerService {
    public Customer registerCustomer(CustomerDto customerDto) throws CustomerExistsException;
    public  String loginCustomer(CustomerLoginDto customerLoginDto);

}
