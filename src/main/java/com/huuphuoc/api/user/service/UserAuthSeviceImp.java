package com.huuphuoc.api.user.service;
import com.huuphuoc.api.common.enums.Roles;
import com.huuphuoc.api.common.enums.Status;
import com.huuphuoc.api.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.api.common.utils.EmailValidator;
import com.huuphuoc.api.user.dto.UserBodyDTO;
import com.huuphuoc.api.user.dto.UserLogInDTO;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthSeviceImp implements UserAuthSevice {
    private final PasswordEndcoder passwordEndcoder;
    private  final IUserRepository iUserRepository;
    private  final EmailValidator emailValidator;
    private final CreateTokenAndSendMailForUserService createTokenAndSendMailForUserService;



    @Override
    public UserBodyDTO RegistrationRequest(UserBodyDTO userBodyDTO) {

        boolean isValiEmail = emailValidator.test(userBodyDTO.getEmail());
        if (!isValiEmail){
            throw  new IllegalStateException("Email không đúng định dạng");
        }
        Optional<User> users = iUserRepository.findByEmail(userBodyDTO.getEmail());
        if (users.isPresent()){
            throw new IllegalStateException("Email này đã được đăng ký!");
        }

        User user = new User();
        user.setUsername(userBodyDTO.getUsername());
        user.setEmail(userBodyDTO.getEmail());
        user.setPassword(passwordEndcoder.bCryptPasswordEncoder().encode(userBodyDTO.getPassword()));
        user.setStatus(Status.UNVERIFIED);
        user.getRoles().add(Roles.USER);
        User userSave = iUserRepository.save(user);
        createTokenAndSendMailForUserService.CreateTokenForUser(userSave);
        return userBodyDTO;
    }
    
}