package com.huuphuoc.webBH.user.service;

import com.huuphuoc.webBH.user.dto.UserBodyDTO;
import com.huuphuoc.webBH.user.dto.UserLogInDTO;

public interface UserAuthSevice {
   UserBodyDTO RegistrationRequest(UserBodyDTO userBodyDTO);
   UserLogInDTO SignInRequest(UserLogInDTO userLogInDTO);
}
