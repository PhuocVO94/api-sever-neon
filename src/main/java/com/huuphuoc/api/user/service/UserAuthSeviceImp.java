package com.huuphuoc.api.user.service;
import com.huuphuoc.api.common.enums.Roles;
import com.huuphuoc.api.common.enums.Status;
import com.huuphuoc.api.common.passwordencoder.PasswordEncoder;
import com.huuphuoc.api.common.utils.EmailValidator;
import com.huuphuoc.api.user.dto.UserBodyDTO;
import com.huuphuoc.api.user.email.service.EmailServiceImpl;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthSeviceImp implements UserAuthSevice {
    private final PasswordEncoder passwordEncoder;
    private  final IUserRepository iUserRepository;
    private  final EmailValidator emailValidator;
    private  final EmailServiceImpl emailService;
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
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userBodyDTO.getPassword()));
        user.setStatus(Status.UNVERIFIED);
        user.getRoles().add(Roles.USER);
        User userSave = iUserRepository.save(user);
        createTokenAndSendMailForUserService.CreateTokenForUser(userSave);
        return userBodyDTO;
    }

    @Override
    public Object ResetPassword(String email) {

        try {
            if (!emailValidator.test(email)) {
                throw  new IllegalStateException("Email không đúng định dạng: " + email);

            }
            User user = iUserRepository.findUserByEmail(email);
            if (user == null){
                throw  new RuntimeException("Tài khoản k tồn tại");
            }

            if (!user.isBlock()) {
                    throw  new RuntimeException( "Tài khoản đã khóa cần liên hệ chủ phần mềm");
            }
//            System.out.println("Check Email User: " + user.getUsername());
            String newPassWord  = "Mkmcbla123";
            user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassWord));
            iUserRepository.save(user);
            emailService.resetPassword(email,newPassWord);

        }catch (NullPointerException e) {
            throw  new NullPointerException("K" + e.getMessage());

        }





        return  "Mk đã được gửi tới Email của bạn!!!";


    }

}