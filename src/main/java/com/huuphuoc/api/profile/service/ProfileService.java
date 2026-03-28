package com.huuphuoc.api.profile.service;
import com.huuphuoc.api.profile.model.ChangePassDTO;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.user.model.User;

import java.util.UUID;



public interface ProfileService  {

    ProfileDTO saveProfile(User user, ProfileDTO profileDTO);
    ProfileDTO getProfile(UUID userID);
    String changePassword(UUID userid, ChangePassDTO changePassDTO);

}
