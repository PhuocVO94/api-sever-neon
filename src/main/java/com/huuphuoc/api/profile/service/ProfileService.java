package com.huuphuoc.api.profile.service;
import com.huuphuoc.api.profile.model.Profile;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.user.model.User;

import java.util.List;
import java.util.UUID;



public interface ProfileService  {
    List<ProfileDTO> findAll();
    ProfileDTO saveProfile(User user, ProfileDTO profileDTO);
    ProfileDTO findById(UUID id);
}
