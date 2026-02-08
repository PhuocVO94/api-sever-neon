package com.huuphuoc.webBH.user.controller;

import com.huuphuoc.webBH.common.utils.ResponseUtility;
import com.huuphuoc.webBH.user.dto.UserBodyDTO;
import com.huuphuoc.webBH.user.dto.UserLogInDTO;
import com.huuphuoc.webBH.user.service.UserAuthSeviceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private  final UserAuthSeviceImp userAuthSeviceImp;
    private  final  ResponseUtility responseUtility;
    @PostMapping("/Register")
    public Object SavedRequest(@RequestBody @Valid UserBodyDTO userBodyDTO){
        return  responseUtility.Get(userAuthSeviceImp.RegistrationRequest(userBodyDTO),HttpStatus.OK);
    }

    @GetMapping("/Login")
    public Object Login(@RequestBody UserLogInDTO userLogInDTO){
        return responseUtility.Get(userAuthSeviceImp.SignInRequest(userLogInDTO),HttpStatus.OK);
    }




}
