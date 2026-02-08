package com.huuphuoc.webBH.user.token.service;


import com.huuphuoc.webBH.user.token.model.ConfirmationToken;
import com.huuphuoc.webBH.user.token.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements  TokenService{

    private  final TokenRepository tokenRepository;

    @Override
    public void SaveConfirmationToken(ConfirmationToken confirmationToken) {
        tokenRepository.save(confirmationToken);

    }
}
