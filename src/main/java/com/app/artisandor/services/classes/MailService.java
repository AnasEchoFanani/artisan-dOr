package com.app.artisandor.services.classes;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender javaMailSender;

    /**
     * Sends an email with the given subject and HTML content to the specified recipient.
     *
     * @param to           the recipient's email address
     * @param subject      the subject of the email
     * @param htmlContent  the HTML content of the email
     * @throws MessagingException if there is a failure in sending the email
     */
    public void sendEmail(String to, String subject, String htmlContent) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true indicates HTML content

            javaMailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send email to {}", to, e);
            throw e; // Re-throw the exception to maintain original behavior
        }
    }
}
