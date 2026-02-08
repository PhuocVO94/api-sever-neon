package com.huuphuoc.api.profile.controller;


import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.common.utils.ResponseUtility;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.profile.service.ProfileServiceImpl;
import com.huuphuoc.api.profile.unit.ProfileEntity;
import com.huuphuoc.api.security.service.CustomerUserDetails;
import com.huuphuoc.api.user.model.User;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfigUrls.URL_USER)
@AllArgsConstructor
public class ProfileController {

    ProfileServiceImpl profileServiceImpl;
    private ResponseUtility responseEntity;



    @PostMapping(ProfileEntity.PROFILE_TABLE)
    public  Object upDateMyProfile(@AuthenticationPrincipal CustomerUserDetails curentUserDetails, @RequestBody ProfileDTO profileDTO){

        User user =  curentUserDetails.getUser();

        User userRfe = new User();
        userRfe.setId(user.getId());
//        System.out.println("Profile Update" + userRfe.getId());
        return responseEntity.Get(profileServiceImpl.saveProfile(userRfe,profileDTO), HttpStatus.OK);

    }

}
