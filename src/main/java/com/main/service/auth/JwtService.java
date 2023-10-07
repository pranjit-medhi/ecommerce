package com.main.service.auth;

import com.main.model.Customer;

public interface JwtService {
    public String generateJwt(Customer customer);
    public String generateUsername(String token);
    public String generateEmailVerificationJwt(Customer customer);
}
