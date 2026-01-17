package com.huuphuoc.webBH.profile.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    @NonNull
    @NotEmpty(message = "Full Name not Blank")
    private String fullName;
    @NonNull
    @NotEmpty(message = "Phone not Blank")
    private String phone;
    @NonNull
    @NotEmpty(message = "Address not Blank")
    private String address;
    private String avatar;

}
