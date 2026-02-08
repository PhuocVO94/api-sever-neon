package com.huuphuoc.webBH.user.service;
import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.webBH.common.utils.EmailValidator;
import com.huuphuoc.webBH.user.dto.UserBodyDTO;
import com.huuphuoc.webBH.user.dto.UserLogInDTO;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
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

    @Override
    public UserLogInDTO SignInRequest(UserLogInDTO userLogInDTO) {

        User user = iUserRepository.findUserByEmail(userLogInDTO.getEmail());
        if (user == null){
            throw new IllegalStateException("Email chưa được đăng ký");
        }
        String passEndCod = passwordEndcoder.bCryptPasswordEncoder().encode(userLogInDTO.getPassword());
        if (!user.getPassword().equals(passEndCod)){
            throw  new IllegalStateException("Password Không đúng");
        }



        return userLogInDTO;
    }

}