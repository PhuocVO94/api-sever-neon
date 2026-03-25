package com.huuphuoc.api.user.email.service;
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

    private final JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Override
    @Async
    public void send(String to, String emailContent) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(emailContent, true); // true để gửi dạng HTML
            helper.setTo(to);
            helper.setSubject("Xác nhận đăng ký tài khoản");
            helper.setFrom("vohuuphuoc1102@gmail.com"); // <-- Thay email của bạn vào đây

            // Gửi mail
            javaMailSender.send(mimeMessage);

            logger.info("Đã gửi mail xác nhận thành công tới: " + to);

        } catch (MessagingException e) {

            throw new IllegalStateException("Không thể gửi email, vui lòng thử lại sau.");
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Đang lỗi chỗ này chàng bộ nhớ" + nullPointerException);
        }

    }

    @Override
    public void resetPassword(String to, String emailContent) {
       try {
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
           helper.setText("Mât  khẩu mới của bạn là: " + emailContent);
           helper.setTo(to);
           helper.setSubject("Reset Password");
           helper.setFrom("vohuuphuoc1102@gmail.com");
           javaMailSender.send(mimeMessage);

       } catch (MessagingException e){
           throw new IllegalStateException("Không thể gửi email, vui lòng thử lại sau.");

       } catch (NullPointerException nullPointerException) {
           throw new NullPointerException("Đang lỗi chỗ này chàng bộ nhớ" + nullPointerException);
       }
    }

}
