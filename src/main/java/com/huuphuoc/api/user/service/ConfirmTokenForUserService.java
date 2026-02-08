package com.huuphuoc.webBH.user.service;


import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.profile.model.Profile;
import com.huuphuoc.webBH.profile.repository.IProfileRepository;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import com.huuphuoc.webBH.user.token.model.ConfirmationToken;
import com.huuphuoc.webBH.user.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmTokenForUserService {

    private  final TokenRepository tokenRepository;
    private  final IUserRepository iUserRepository;
    private  final IProfileRepository iProfileRepository;
    public Boolean HandlerConfirmTokenForUser(String token){
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);
        if (confirmationToken == null){
            throw  new IllegalStateException("Không tìm thấy token");
        }

        // 2. Kiểm tra token đã xác thực chưa hoặc hết hạn chưa
        if (confirmationToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw  new IllegalStateException("Token đã hết hạn");

        }
        if (confirmationToken.getComfirmAt() != null){
            throw  new IllegalStateException("Token đã được kích hoạt trước đó");
        }

//        CHECK USER TRONG TOKEN
        User user = confirmationToken.getUser();


        if (user.isBlock() == true) {
            throw  new IllegalStateException("Tài khoản đã được ACTIVE trước đó");
        }
        if (user.getStatus() == Status.ACTIVE){
            throw new IllegalStateException("Trạng thái người dùng đã được ACTIVE");
        }
        user.setBlock(true);
        user.setStatus(Status.ACTIVE);
        confirmationToken.setComfirmAt(confirmationToken.getToken());

      User userSave =  iUserRepository.save(user);

        Profile profile = new Profile();
        profile.setUser(userSave);
        iProfileRepository.save(profile);

      tokenRepository.save(confirmationToken);
        if (userSave.getStatus() == Status.ACTIVE  && userSave.isBlock() == true){
            return  true;
        }
        else {
            return !true;
        }

    };
}
