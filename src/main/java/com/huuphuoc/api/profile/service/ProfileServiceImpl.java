package com.huuphuoc.api.profile.service;

import com.huuphuoc.api.config.ModelMapperConfig;
import com.huuphuoc.api.profile.model.Profile;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.profile.repository.IProfileRepository;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProfileServiceImpl  implements ProfileService{


    IProfileRepository iprofileRepository;
    IUserRepository iUserRepository;
    ModelMapperConfig modelMapperConfig;

    public ProfileServiceImpl(IProfileRepository iprofileRepository, IUserRepository iUserRepository, ModelMapperConfig modelMapperConfig) {
        this.iprofileRepository = iprofileRepository;
        this.iUserRepository = iUserRepository;
        this.modelMapperConfig = modelMapperConfig;
    }

    @Override
    public List<ProfileDTO> findAll() {
        return List.of();
    }

    @Override
    public ProfileDTO saveProfile( User user, ProfileDTO profileDTO) {

        User userR = iUserRepository.getReferenceById(user.getId());
        Profile profile = new Profile();
        profile.setFullName(profileDTO.getFullName());
        profile.setPhone(profileDTO.getPhone());
        profile.setAddress(profileDTO.getAvatar());
        profile.setAvatar(profileDTO.getAvatar());
        profile.setUser(userR);
        System.out.println("Check Profile trong Save Profile" + profile.getUser().getId());
        iprofileRepository.save(profile);
        return profileDTO;
    }

    @Override
    public ProfileDTO findById(UUID id) {
        Optional <Profile> profiles = iprofileRepository.findById(id);

        if (profiles.isEmpty()){
            throw  new IllegalStateException("Không tìm thấy profile");
        }

        System.out.println("Luồng đang đúng");

        return null;
    }
}

