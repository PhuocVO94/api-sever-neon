package com.huuphuoc.api.user.email.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void send(String to, String emailContent);

    void resetPassword(String to, String emailContent) throws MessagingException;
}
