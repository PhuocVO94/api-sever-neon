package com.huuphuoc.webBH.user.service;


import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.user.dto.UserCreateRequestDTO;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@AllArgsConstructor
public class UserCreateRequestImlp implements UserCreateRequestSevice {
    IUserRepository iUserRepository;

    public UserCreateRequestImlp(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public String HandleUserCreateRequest(UserCreateRequestDTO userCreateRequestDTO) {
        String messenger = "";
        Optional<User> users = iUserRepository.findByEmail(userCreateRequestDTO.getEmail());
        if (users.isEmpty()){
            User user = new User();
            user.setUsername(userCreateRequestDTO.getUsername());
            user.setEmail(userCreateRequestDTO.getEmail());
            user.setPassword(userCreateRequestDTO.getPassword());
            user.setStatus(Status.ACTIVE);
            user.getRoles().add(Roles.USER);
            iUserRepository.save(user);
            messenger ="Đăng ký tài khoản thành công";

            return messenger;
        }else {
            messenger ="Tài khoản đã tồn tại !!!";
            return messenger;

        }

    }
}
