package com.huuphuoc.webBH.profile.model;

import com.huuphuoc.webBH.common.model.BaseEntity;
import com.huuphuoc.webBH.profile.unit.ProfileEntity;
import com.huuphuoc.webBH.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = ProfileEntity.PROFILE_TABLE)
public class Profile extends BaseEntity {

    @Column(name = ProfileEntity.PROFILE_FULLNAME)
    private String fullName;
    @Column(name = ProfileEntity.PROFILE_PHONE)
    private String phone;
    @Column(name = ProfileEntity.PROFILE_ADDRESS)
    private String address;
    @Column(name = ProfileEntity.PROFILE_AVATAR)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;




}
