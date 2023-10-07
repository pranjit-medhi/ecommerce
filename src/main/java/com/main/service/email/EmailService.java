package com.main.service.email;

import com.main.exception.MailFailureException;
import com.main.model.VerificationToken;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public SimpleMailMessage makeMailMessage();
    public void sendVerificationEmail (VerificationToken verificationToken) throws MailFailureException;


    }
