package com.huuphuoc.api.profile.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePassDTO {


    @NotNull
    @Size(min = 8, max = 30)
    @NotEmpty(message = "Vui lòng nhập pass word.")
    String oldPassword;

    @NotNull
    @Size(min = 8, max = 30)
    @NotEmpty(message = "Vui lòng nhập pass word.")
    String newPassword;
    @NotNull
    @Size(min = 8, max = 30)
    @NotEmpty(message = "Vui lòng nhập pass word.")
    String confirmPassword;
}
