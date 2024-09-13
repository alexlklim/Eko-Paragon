package com.alex.eko.paragon.old.emails;


import com.alex.eko.paragon.old.security.domain.Token;
import com.alex.eko.paragon.old.security.domain.User;
import com.alex.eko.paragon.old.security.utils.UtilsSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceSender {
    private final EmailService emailService;


    public void sendConfirmationEmail(User user, Token token){
        emailService.createMail(
                EmailStructure.builder()
                        .email(user.getEmail())
                        .emailType(EmailType.CONFIRM_EMAIL)
                        .build(),
                emailService.createBody(
                        EmailType.CONFIRM_EMAIL,
                        UtilsSecurity.ENDPOINT_ACTIVATE + token.getToken().toString())
        );
    }


    public void sendNotificationEmailThatPasswordWasChanged(String email){
        emailService.createMail(
                EmailStructure.builder()
                        .email(email)
                        .emailType(EmailType.PASSWORD_WAS_CHANGED)
                        .build(),
                emailService.createBody(EmailType.PASSWORD_WAS_CHANGED)
        );
    }


    public void sendLinkToPasswordRecovery(User user, Token token){
        emailService.createMail(
                EmailStructure.builder()
                        .email(user.getEmail())
                        .emailType(EmailType.FORGOT_PASSWORD)
                        .build(),
                emailService.createBody(
                        EmailType.FORGOT_PASSWORD,
                        UtilsSecurity.ENDPOINT_RECOVERY + token.getToken().toString())
        );
    }

}
