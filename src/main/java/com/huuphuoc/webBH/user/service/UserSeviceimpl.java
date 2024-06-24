package com.huuphuoc.webBH.user.service;


import com.huuphuoc.webBH.common.config.ConfigModelMapper;
import com.huuphuoc.webBH.oder.repository.IOrderRepository;
import com.huuphuoc.webBH.user.dto.UserDTO;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class UserSeviceimpl implements UserService {
    private final IUserRepository iUserRepository;

    @Autowired
    ConfigModelMapper configModelMapper;

    public UserSeviceimpl(IUserRepository iUserRepository, IOrderRepository iOderRepository) {

        this.iUserRepository = iUserRepository;

    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return this.iUserRepository;
    }


    @Override
    public List<UserDTO> findAll() {
        List<User> users = iUserRepository.findAll();
        return users.stream().map(u -> configModelMapper.map(u, UserDTO.class)).toList();
    }


    public User save(User user) {
        return this.iUserRepository.save(user);
    }

    @Override
    public ModelMapper modelMapper() {
        return configModelMapper;
    }

    @Override
    public User findByID(UUID uuid) {
        return null;
    }


}
