package com.huuphuoc.api.profile.model;


import com.huuphuoc.api.common.enums.Gender;
import com.huuphuoc.api.common.utils.DateTimeFomat;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileDTO {
    @NonNull
    @NotEmpty(message = "Full Name not Blank")
    private String fullName;
    @NonNull
    @NotEmpty(message = "Gender not Blank")
    private Gender gender;
    @NonNull
    @NotEmpty(message = "Phone not Blank")
    private String phone;
    @NonNull
    @NotEmpty(message = "BirthDay not Blank")
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    private Date birthDay;
    private String avatar;

}
