package com.huuphuoc.api.user.token.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.huuphuoc.api.common.utils.DateTimeFomat;
import com.huuphuoc.api.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="confirm_token")
public class ConfirmationToken {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = TokenEntity.STRING_TOKEN)
    private  String token;



    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = TokenEntity.CRERATED_TOKEN)
    private LocalDateTime createdAt;



    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = TokenEntity.EXPIRED_TOKEN)
    private  LocalDateTime expiredAt;

    @Column(name = "ConfirmToken")
    private String comfirmAt =  null;


    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading để tối ưu hiệu năng
    @JoinColumn(name = "user_id", nullable = false) // Tên cột khóa ngoại trong bảng Token
    @ToString.Exclude // Ngăn Lombok tạo vòng lặp vô tận khi in log
    private User user;



    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
