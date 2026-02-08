package com.huuphuoc.webBH.user.token.service;

import com.huuphuoc.webBH.user.token.model.ConfirmationToken;

public interface TokenService {

    void SaveConfirmationToken(ConfirmationToken confirmationToken);
}
