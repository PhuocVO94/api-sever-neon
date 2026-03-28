package com.huuphuoc.api.profile.service;

import com.huuphuoc.api.common.passwordencoder.PasswordEncoder;
import com.huuphuoc.api.config.ModelMapperConfig;
import com.huuphuoc.api.profile.model.ChangePassDTO;
import com.huuphuoc.api.profile.model.Profile;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.profile.repository.IProfileRepository;
import com.huuphuoc.api.user.model.User;
import com.huuphuoc.api.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service

public class ProfileServiceImpl  implements ProfileService {


    IProfileRepository iprofileRepository;
    IUserRepository iUserRepository;
    ModelMapperConfig modelMapperConfig;
    PasswordEncoder passwordEncoder;


    public ProfileServiceImpl(IProfileRepository iprofileRepository, IUserRepository iUserRepository, ModelMapperConfig modelMapperConfig, PasswordEncoder passwordEncoder) {
        this.iprofileRepository = iprofileRepository;
        this.iUserRepository = iUserRepository;
        this.modelMapperConfig = modelMapperConfig;
        this.passwordEncoder = passwordEncoder;
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


    @Override
    public String changePassword(UUID userid, ChangePassDTO changePassDTO) {


        User user = iUserRepository.findUserById(userid);
        System.out.println("Check" + user.getEmail());
        if (user == null) {
            throw  new RuntimeException("Tài khoản k tồn tại!");
        }


        String bcyptOldPassWord = user.getPassword();
        Boolean result = passwordEncoder.bCryptPasswordEncoder().matches(changePassDTO.getOldPassword(),bcyptOldPassWord);
        if (!result) {
            throw  new RuntimeException("Mật  hiện tại không đúng");
        }

        if (!changePassDTO.getNewPassword().equals(changePassDTO.getConfirmPassword())) {
            throw  new RuntimeException("Mật khẩu mới không đúng");
        }
        String bcyptNewPassWord =  passwordEncoder.bCryptPasswordEncoder().encode(changePassDTO.getNewPassword());

        user.setPassword(bcyptNewPassWord);
        iUserRepository.save(user);

        return "Đổi mật khẩu thành công: " + user.getUsername();
    }


}

