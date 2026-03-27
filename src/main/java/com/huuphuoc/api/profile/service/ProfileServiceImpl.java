package com.huuphuoc.api.profile.service;

import com.huuphuoc.api.common.enums.Gender;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProfileServiceImpl  implements ProfileService {


    IProfileRepository iprofileRepository;
    IUserRepository iUserRepository;
    ModelMapperConfig modelMapperConfig;

    public ProfileServiceImpl(IProfileRepository iprofileRepository, IUserRepository iUserRepository, ModelMapperConfig modelMapperConfig) {
        this.iprofileRepository = iprofileRepository;
        this.iUserRepository = iUserRepository;
        this.modelMapperConfig = modelMapperConfig;
    }


    @Override
    public ProfileDTO saveProfile(User user, ProfileDTO profileDTO) {
        User userR = iUserRepository.getReferenceById(user.getId());
        Date currDate = new Date();
        if (profileDTO.getBirthDay().after(currDate)) {
            throw new RuntimeException("Năm sinh bị sai");
        }

        Profile profile = iprofileRepository.findByUserId(user.getId())
                .orElseGet(() -> new Profile());
        if (profile.getUser() == null) {
            profile.setUser(userR);
        }

        profile.setFullName(profileDTO.getFullName());
        profile.setGender(profileDTO.getGender());
        profile.setBirthday(profileDTO.getBirthDay());
        profile.setPhone(profileDTO.getPhone());
        profile.setAvatar(profileDTO.getAvatar());
        iprofileRepository.save(profile);
        return profileDTO;
    }


    @Override
    public ProfileDTO getProfile(UUID userid) {

        Profile profile = iprofileRepository.findById(userid).orElseThrow(() ->
                new RuntimeException("Không tìm thấy thông tin Profile cho User"));

        ProfileDTO profileDTO =  new ProfileDTO();
            profileDTO.setFullName(profile.getFullName());
            profileDTO.setPhone(profile.getPhone());
            profileDTO.setBirthDay(profile.getBirthday());
            profileDTO.setGender(profile.getGender());
            profileDTO.setAvatar(profile.getAvatar());
        return profileDTO;
    }


}

