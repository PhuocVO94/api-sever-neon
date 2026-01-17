package com.huuphuoc.webBH.user.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDTO {
    @NotNull
    @Size( max = 30)
    @NotEmpty(message = "User name not Blank")
        private String email;
    @NotNull
    @Size( max = 30)
    @NotEmpty(message = "User name not Blank")
        private String password;
    @NotNull
    @Size( max = 30)
    @NotEmpty(message = "User name not Blank")
        private String username;



}
