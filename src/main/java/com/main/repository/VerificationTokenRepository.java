package com.main.repository;

import com.main.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long> {
}
