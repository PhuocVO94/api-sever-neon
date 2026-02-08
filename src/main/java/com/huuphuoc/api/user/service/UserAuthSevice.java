package com.huuphuoc.api.user.service;

import com.huuphuoc.api.user.dto.UserBodyDTO;
import com.huuphuoc.api.user.dto.UserLogInDTO;

public interface UserAuthSevice {
   UserBodyDTO RegistrationRequest(UserBodyDTO userBodyDTO);
   UserLogInDTO SignInRequest(UserLogInDTO userLogInDTO);
}
