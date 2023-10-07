package com.main.service.email;


import com.main.exception.MailFailureException;
import com.main.model.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    @Value("${email.from}")
    private String fromAddress;

    @Value("${app.url}")
    private String url;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public SimpleMailMessage makeMailMessage() {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(fromAddress);
        return simpleMessage;
    }
        public void sendVerificationEmail(VerificationToken verificationToken) throws MailFailureException{
        SimpleMailMessage simpleMessage = makeMailMessage();
        simpleMessage.setTo(verificationToken.getCustomer().getEmail());
        simpleMessage.setSubject("Verify your email id");
        simpleMessage.setText("Please click on the link to verify your email "+url+"auth/verify/token="+verificationToken.getToken());
        try{
            javaMailSender.send(simpleMessage);
        }catch (MailException e)
        {
            throw new MailFailureException();
        }
    }
}
