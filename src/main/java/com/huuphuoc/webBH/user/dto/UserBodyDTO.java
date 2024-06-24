package com.huuphuoc.webBH.user.dto;


import com.huuphuoc.webBH.common.annotation.JsonEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBodyDTO {

    @NotNull
    @Size(min = 8, max = 30)
    @NotEmpty(message = "User name not Blank")
    private String username;
    @NotNull
    @NotEmpty(message = "Password Not Blank")
    @Size(min = 8, max = 16)
    private String password;
    @NotNull
    @NotEmpty(message = "Phone number not blank")
    private String phoneNumber;
    @JsonEmail()
    @Email(message = "Email incorrectly")
    private String email;

}
