package com.huuphuoc.webBH.profile.service;

import com.huuphuoc.webBH.config.ModelMapperConfig;
import com.huuphuoc.webBH.profile.model.Profile;
import com.huuphuoc.webBH.profile.model.ProfileDTO;
import com.huuphuoc.webBH.profile.repository.IProfileRepository;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class ProfileServiceImpl  implements ProfileService{

    @Autowired
    IProfileRepository iprofileRepository;
    ModelMapperConfig modelMapperConfig;
    IUserRepository iUserRepository;

    public ProfileServiceImpl(IProfileRepository iprofileRepository, ModelMapperConfig modelMapperConfig, IUserRepository iUserRepository) {
        this.iprofileRepository = iprofileRepository;
        this.modelMapperConfig = modelMapperConfig;
        this.iUserRepository = iUserRepository;
    }


    @Override
    public List<ProfileDTO> findAll() {
        return List.of();
    }

    @Override
    public Profile saveProfile(UUID id, ProfileDTO dto) {
        User user = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + id));
        Profile profile = iprofileRepository.findById(id).orElse(null);
        if (profile == null){
            profile = new Profile();
            profile.setUser(user);
            profile.setFullName(dto.getFullName());
            profile.setPhone(dto.getPhone());
            profile.setAddress(dto.getAddress());
            profile.setAvatar(dto.getAvatar());

            iprofileRepository.save(profile);
        }else {
            System.out.println("Đã cập nhập đầy đủ rồi->");
        }

        return profile;
    }

    @Override
    public ProfileDTO findById(UUID id) {
        return null;
    }
}

