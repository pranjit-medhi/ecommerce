package com.main.controller.auth;

import com.main.dto.CustomerDto;
import com.main.dto.CustomerLoginDto;
import com.main.dto.CustomerLoginResponse;
import com.main.exception.CustomerExistsException;
import com.main.model.Customer;
import com.main.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerService customerService;
    @Autowired
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Validated @RequestBody CustomerDto customerDto) throws CustomerExistsException {
        try{
            customerService.registerCustomer(customerDto);
            return new ResponseEntity(HttpStatus.CREATED);

        }
        catch (CustomerExistsException e)
        {
           return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public  ResponseEntity loginUser(@Validated @RequestBody CustomerLoginDto customerLoginDto) {
        String jwt = customerService.loginCustomer(customerLoginDto);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        CustomerLoginResponse response = new CustomerLoginResponse();
        response.setJwt(jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getuser")
    public ResponseEntity<Customer> getLoggedInUser(@AuthenticationPrincipal Customer customer)
    {
        return  new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
