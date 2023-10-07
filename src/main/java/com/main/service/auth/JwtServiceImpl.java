package com.main.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.main.model.Customer;
import com.main.service.auth.JwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Date;
@Service
public class JwtServiceImpl  implements JwtService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private final String USERNAME_KEY = "USERNAME";
    private final String EMAIL_KEY = "EMAIL";
    @PostConstruct
    public void postContruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJwt(Customer customer)
    {
        return JWT.create()
                .withClaim(USERNAME_KEY, customer.getUsername())
                .withExpiresAt(new Date( System.currentTimeMillis() + (1000 *expiryInSeconds) ))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String generateEmailVerificationJwt(Customer customer)
    {
        return JWT.create()
                .withClaim(EMAIL_KEY, customer.getEmail())
                .withExpiresAt(new Date( System.currentTimeMillis() + (1000 *expiryInSeconds) ))
                .withIssuer(issuer)
                .sign(algorithm);

    }
    public String generateUsername(String token)
    {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
}
