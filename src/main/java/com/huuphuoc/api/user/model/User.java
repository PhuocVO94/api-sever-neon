package com.huuphuoc.webBH.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.model.BaseEntity;
import com.huuphuoc.webBH.oder.model.Order;
//import com.huuphuoc.webBH.user.enums;
import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.profile.model.Profile;
import com.huuphuoc.webBH.user.token.model.ConfirmationToken;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = UserEntity.user.TABLE_NAME)

public class User extends BaseEntity {

    @Column(name = UserEntity.user.COLUMN_NAME)
    private String username;


    @Column(name = UserEntity.user.COLUMN_PASSWORD)
    private String password;


    @Column(name = UserEntity.user.COLUMN_EMAIL)
    private String email;


    @Column(name = UserEntity.user.COLUMN_BOCK)
    private boolean isBlock = false;


//    Trạng thái vai trò của User

    @ElementCollection(fetch = FetchType.EAGER) // EAGER: Lấy user là lấy luôn quyền
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = UserEntity.user.COLUNM_ROLES) // Tên cột trong bảng phụ
    private Set<Roles> roles = new HashSet<>(Collections.singleton(Roles.USER));




//    Trạng thái hoạt động của user
    @Enumerated(EnumType.STRING)
    @Column(name = UserEntity.user.COLUNM_STATUS)
    private Status status = Status.UNVERIFIED;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Ngăn Lombok tạo vòng lặp vô tận
    @EqualsAndHashCode.Exclude // Ngăn Lombok so sánh list này
    @JsonIgnore // Ngăn việc khi lấy User lại load toàn bộ Token ra JSON (gây nặng hoặc lỗi vòng lặp)
    private List<ConfirmationToken> tokens;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
//        this.phoneNumber = phoneNumber;
        this.email = email;
    }


}
