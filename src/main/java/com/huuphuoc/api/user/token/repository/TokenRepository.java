package com.huuphuoc.api.user.token.repository;


import com.huuphuoc.api.user.token.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<ConfirmationToken, UUID> {
    ConfirmationToken findByToken(String token);
}
