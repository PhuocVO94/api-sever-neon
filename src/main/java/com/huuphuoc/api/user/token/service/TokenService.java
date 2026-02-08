package com.huuphuoc.api.user.token.service;

import com.huuphuoc.api.user.token.model.ConfirmationToken;

public interface TokenService {

    void SaveConfirmationToken(ConfirmationToken confirmationToken);
}
