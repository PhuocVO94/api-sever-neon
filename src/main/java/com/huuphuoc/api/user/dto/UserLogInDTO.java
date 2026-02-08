package com.huuphuoc.api.user.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInDTO {

    @NotNull
    @NotEmpty(message = "Email không được để trống !!!")
    private String email;
    @NotNull
    @Size(min = 8, max = 16, message = "Tối đa 16 ký tự")
    @NotEmpty(message = "Password không được để trống !!!")
    private String password;
}
