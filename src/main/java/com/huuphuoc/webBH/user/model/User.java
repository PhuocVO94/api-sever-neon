package com.huuphuoc.webBH.user.model;


import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.model.BaseEntity;
import com.huuphuoc.webBH.oder.model.Order;
//import com.huuphuoc.webBH.user.enums;
import com.huuphuoc.webBH.common.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = UserEntity.user.TABLE_NAME)

public class User extends BaseEntity {

    @Column(name = UserEntity.user.COLUMN_NAME)
    private String username;


    @Column(name = UserEntity.user.COLUMN_PASSWORD, length = 30)
    private String password;


//

    @Column(name = UserEntity.user.COLUMN_EMAIL)
    private String email;


//    Trạng thái vai trò của User

    @ElementCollection(fetch = FetchType.EAGER) // EAGER: Lấy user là lấy luôn quyền
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "role") // Tên cột trong bảng phụ
    private Set<Roles> roles = new HashSet<>(Collections.singleton(Roles.USER));




//    Trạng thái hoạt động của user
    @Enumerated(EnumType.STRING)
    @Column(name = UserEntity.user.COLUNM_STATUS)
    private Status status = Status.UNVERIFIED;


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
