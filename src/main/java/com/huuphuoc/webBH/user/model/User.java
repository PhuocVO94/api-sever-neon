package com.huuphuoc.webBH.user.model;


import com.huuphuoc.webBH.oder.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = UserEntity.user.TABLE_NAME)

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = UserEntity.user.COLUMN_NAME)
    private String username;


    @Column(name = UserEntity.user.COLUMN_PASSWORD, length = 30)
    private String password;

    @Column(name = UserEntity.user.COLUMN_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = UserEntity.user.COLUMN_EMAIL)

    private String email;


    /* OneTomany  đùng để để liên kết dữ khoá ngoại từ bản Oder(Id của lớp user là khoá ngoại của lớp Oder) liên kết tới*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String phoneNumber, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }


}
