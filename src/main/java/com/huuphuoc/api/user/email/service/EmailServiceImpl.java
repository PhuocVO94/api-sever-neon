package com.huuphuoc.webBH.user.email.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Override
    @Async
    public void send(String to, String emailContent) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            // Thiết lập thông tin
            helper.setText(emailContent, true); // true để gửi dạng HTML
            helper.setTo(to);
            helper.setSubject("Xác nhận đăng ký tài khoản");
            helper.setFrom("vohuuphuoc1102@gmail.com"); // <-- Thay email của bạn vào đây

            // Gửi mail
            mailSender.send(mimeMessage);

            logger.info("Đã gửi mail xác nhận thành công tới: " + to);

        } catch (MessagingException e) {
            logger.error("Gửi mail thất bại tới: " + to, e);
            throw new IllegalStateException("Không thể gửi email, vui lòng thử lại sau.");
        }

    }
}
