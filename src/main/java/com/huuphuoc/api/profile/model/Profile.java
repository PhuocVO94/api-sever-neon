package com.huuphuoc.api.profile.model;

import com.huuphuoc.api.common.enums.Gender;
import com.huuphuoc.api.common.model.BaseEntity;
import com.huuphuoc.api.common.utils.DateTimeFomat;
import com.huuphuoc.api.profile.unit.ProfileEntity;
import com.huuphuoc.api.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.module.Configuration;
import java.lang.reflect.Field;
import java.sql.Date;

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
    @Column(name = ProfileEntity.PROFILE_GENDER,columnDefinition = "SMALLINT")
    @Enumerated(value = EnumType.ORDINAL)
    private Gender gender;

    @Column(name = ProfileEntity.PROFILE_PHONE)
    private String phone;
    @Column(name = ProfileEntity.PROFILE_BIRTHDAY)

    private Date birthday;
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = ProfileEntity.PROFILE_AVATAR)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;




}
