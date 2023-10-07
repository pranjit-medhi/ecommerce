package com.main.service;

import com.main.dto.CustomerDto;
import com.main.dto.CustomerLoginDto;
import com.main.exception.CustomerExistsException;
import com.main.exception.MailFailureException;
import com.main.model.Customer;
import com.main.model.VerificationToken;
import com.main.repository.CustomerRepository;
import com.main.repository.VerificationTokenRepository;
import com.main.service.auth.EncryptionService;
import com.main.service.auth.JwtService;
import com.main.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EncryptionService encryptionService;
    private  final EmailService emailService;
    private JwtService jwtService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, VerificationTokenRepository verificationTokenRepository, EncryptionService encryptionService, EmailService emailService, JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.encryptionService = encryptionService;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    @Override
    public Customer registerCustomer(CustomerDto customerDto) throws CustomerExistsException {
        if(customerRepository.findByEmail(customerDto.getEmail()).isPresent()
                || customerRepository.findByUsername(customerDto.getUsername()).isPresent())
        {
            throw new CustomerExistsException();
        }
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setUsername(customerDto.getUsername());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(encryptionService.encryptPassword(customerDto.getPassword()));
        VerificationToken token = createVerificationToken(customer);
        try {
            emailService.sendVerificationEmail(token);
        } catch (MailFailureException e) {
            throw new RuntimeException(e);
        }
        verificationTokenRepository.save(token);
        return customerRepository.save(customer);
    }


    @Override
    public String loginCustomer(CustomerLoginDto customerLoginDto) {
        Optional<Customer> user = customerRepository.findByUsername(customerLoginDto.getUsername());
        if (user.isPresent()) {
            Customer customer = user.get();
            if (encryptionService.verifyPassword(customerLoginDto.getPassword(), customer.getPassword()))
            {
                return jwtService.generateJwt(customer);
            }
        }
        return null;
    }

    private VerificationToken createVerificationToken(Customer customer)
    {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setCustomer(customer);
        verificationToken.setToken(jwtService.generateEmailVerificationJwt(customer));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        customer.getVerificationTokenList().add(verificationToken);
        return verificationToken;
    }
}
