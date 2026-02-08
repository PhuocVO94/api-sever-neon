package com.huuphuoc.webBH.user.controller;


import com.huuphuoc.webBH.common.utils.ResponseUtility;
import com.huuphuoc.webBH.user.service.ConfirmTokenForUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfirmTokenForUserController {


    private  final ResponseUtility responseUtility;
    private  final ConfirmTokenForUserService confirmTokenForUserService;


    @PostMapping("/confirm/{token}")
    public Object ConfirmToken(@PathVariable("token") @Valid String token){
        return responseUtility.Get(confirmTokenForUserService.HandlerConfirmTokenForUser(token), HttpStatus.OK);
    }
}
