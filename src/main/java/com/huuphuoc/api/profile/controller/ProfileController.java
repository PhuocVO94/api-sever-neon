package com.huuphuoc.webBH.profile.controller;


import com.huuphuoc.webBH.common.utils.ResponseUtility;
import com.huuphuoc.webBH.profile.model.Profile;
import com.huuphuoc.webBH.profile.model.ProfileDTO;
import com.huuphuoc.webBH.profile.service.ProfileService;
import com.huuphuoc.webBH.profile.unit.ProfileEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ProfileEntity.PROFILE_TABLE)
public class ProfileController {
    @Autowired
    ProfileService profileService;
    private ResponseUtility responseEntity;

    public ProfileController(ProfileService profileService, ResponseUtility responseUtility) {
        this.profileService = profileService;
        this.responseEntity = responseUtility;
    }



    @PostMapping("/save/{id}")
    public  Object Save(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ProfileDTO profile){
        return responseEntity.Get(profileService.saveProfile(id,profile), HttpStatus.OK);

    }

}
