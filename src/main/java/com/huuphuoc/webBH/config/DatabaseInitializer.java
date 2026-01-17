package com.huuphuoc.webBH.config;

import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final IUserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@gmail.com";

        Optional<User> user =userRepository.findByEmail(adminEmail);
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.ADMIN);
        roles.add(Roles.USER);
        if (user.isEmpty()){
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setUsername("admin");
            admin.setPassword("12345678");
            admin.setRoles(roles);
            admin.setStatus(Status.ACTIVE);
            userRepository.save(admin);
            System.out.println(">>> Đã khởi tạo tài khoản ADMIN mặc định: " + admin.getUsername() + admin.getRoles());

        }

    }
}
;