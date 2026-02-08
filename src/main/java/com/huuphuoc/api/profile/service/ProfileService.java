package com.huuphuoc.webBH.profile.service;
import com.huuphuoc.webBH.profile.model.Profile;
import com.huuphuoc.webBH.profile.model.ProfileDTO;

import java.util.List;
import java.util.UUID;



public interface ProfileService  {
    List<ProfileDTO> findAll();
    Profile saveProfile(UUID id, ProfileDTO dto);
    ProfileDTO findById(UUID id);
}
