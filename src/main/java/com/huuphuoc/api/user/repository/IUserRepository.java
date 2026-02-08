package com.huuphuoc.api.user.repository;


import com.huuphuoc.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findUserById(UUID uuid);

    User findUserByEmail(String email);


}
