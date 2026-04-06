package com.huuphuoc.api.profile.controller;


import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.common.exception.DataNotFoundException;
import com.huuphuoc.api.common.exception.GlobalExceptionHandler;
import com.huuphuoc.api.common.model.ResponEntity;
import com.huuphuoc.api.common.utils.ResponseUtility;
import com.huuphuoc.api.profile.model.ChangePassDTO;
import com.huuphuoc.api.profile.model.ProfileDTO;
import com.huuphuoc.api.profile.service.ProfileServiceImpl;
import com.huuphuoc.api.profile.unit.ProfileEntity;
import com.huuphuoc.api.security.service.CustomerUserDetails;
import com.huuphuoc.api.user.model.User;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiConfigUrls.URL_USER)
@AllArgsConstructor
public class ProfileController {

    ProfileServiceImpl profileServiceImpl;
    private ResponseUtility responseEntity;



    @PostMapping(ProfileEntity.PROFILE_TABLE)
    public  Object upDateMyProfile(
            @AuthenticationPrincipal CustomerUserDetails curentUserDetails,
            @RequestBody ProfileDTO profileDTO) throws DataNotFoundException, Exception{
        User user =  curentUserDetails.getUser();

        User userRfe = new User();
        userRfe.setId(user.getId());
        return responseEntity.Get(profileServiceImpl.saveProfile(userRfe,profileDTO), HttpStatus.OK);

    }
    @GetMapping(ProfileEntity.PROFILE_GET_TABLE)
    public ResponseEntity<ResponEntity> getProfile(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails) throws DataNotFoundException,Exception   {
            UUID userId = customerUserDetails.getUser().getId();
            if (userId == null) {
                return responseEntity.Error("Không tìm thấy người dùng!!!",HttpStatus.UNAUTHORIZED);
            }
           return  responseEntity.Get(profileServiceImpl.getProfile(userId),HttpStatus.OK);


    }
    @PutMapping(ProfileEntity.PROFILE_CHANCE_PASSWORD)
    public ResponseEntity<ResponEntity> changePassword(
            @AuthenticationPrincipal CustomerUserDetails customerUserDetails,
            @RequestBody ChangePassDTO changePassDTO) throws DataNotFoundException, Exception {
        UUID userID = customerUserDetails.getUser().getId();
        return  responseEntity.Get(profileServiceImpl.changePassword(userID, changePassDTO), HttpStatus.OK);
    }

}
