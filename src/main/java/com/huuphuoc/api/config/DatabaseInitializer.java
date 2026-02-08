package com.huuphuoc.api.config;

import com.huuphuoc.api.common.enums.Roles;
import com.huuphuoc.api.common.enums.Status;
import com.huuphuoc.api.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
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
    private  final PasswordEndcoder passwordEndcoder;



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
            admin.setPassword(passwordEndcoder.bCryptPasswordEncoder().encode("123456789"));
            admin.setRoles(roles);
            admin.setStatus(Status.ACTIVE);
            admin.setBlock(true);
            userRepository.save(admin);
            System.out.println(">>> Đã khởi tạo tài khoản ADMIN mặc định: " + admin.getUsername() + admin.getRoles());

        }

    }
}
;