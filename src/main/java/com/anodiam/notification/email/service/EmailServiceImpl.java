package com.anodiam.notification.email.service;

import com.anodiam.core.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.sender}")
    private String sender;

    // To send a simple email
    public void sendSimpleMail(NotificationRequest notificationRequest)
    {

        // Try block to check for exceptions
        try {
            Context context = new Context();
            context.setVariable("notificationRequest", notificationRequest);

            String body = templateEngine.process(EmailTemplateResolver.getTemplate(notificationRequest.getNotificationType()), context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(sender);
            helper.setText(body, true);
            helper.setTo(notificationRequest.getRecipientEmail());
            helper.setSubject(notificationRequest.getSubject());
            javaMailSender.send(mimeMessage);
            LOGGER.info("Mail Sent Successfully...");
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            LOGGER.error("Error while Sending Mail", e);
        }
    }

    // To send an email with attachment
    public void sendMailWithAttachment(NotificationRequest notificationRequest)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            Context context = new Context();
            context.setVariable("notificationRequest", notificationRequest);

            String body = templateEngine.process(EmailTemplateResolver.getTemplate(notificationRequest.getNotificationType()), context);
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(notificationRequest.getRecipientEmail());
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(
                    notificationRequest.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(notificationRequest.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            LOGGER.info("Mail Sent Successfully...");
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            LOGGER.error("Error while Sending Mail", e);
        }
    }
}
