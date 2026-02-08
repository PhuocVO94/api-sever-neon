package com.huuphuoc.api.user.controller;


import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.common.utils.ResponseUtility;
import com.huuphuoc.api.user.service.ConfirmTokenForUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConfigUrls.URL_AUTH)
public class ConfirmTokenForUserController {

    private  final ResponseUtility responseUtility;
    private  final ConfirmTokenForUserService confirmTokenForUserService;


    @PostMapping("/confirm/{token}")
    public Object ConfirmToken(@PathVariable("token") @Valid String token){
        return responseUtility.Get(confirmTokenForUserService.HandlerConfirmTokenForUser(token), HttpStatus.OK);
    }
}
