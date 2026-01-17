package com.huuphuoc.webBH.user.controller;

import com.huuphuoc.webBH.common.Util.UserUtilconfig;
import com.huuphuoc.webBH.common.utils.ResponseUtility;
import com.huuphuoc.webBH.user.dto.UserCreateRequestDTO;
import com.huuphuoc.webBH.user.service.UserCreateRequestImlp;
import com.huuphuoc.webBH.user.service.UserCreateRequestSevice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")


public class UserCreateRequestController {
    UserCreateRequestSevice userCreateRequestSevice;
    ResponseUtility responseUtility;

    public UserCreateRequestController(UserCreateRequestSevice userCreateRequestSevice, ResponseUtility responseUtility) {
        this.userCreateRequestSevice = userCreateRequestSevice;
        this.responseUtility = responseUtility;
    }

    @PostMapping("/Register")
    public Object SavedRequest(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO){
        System.out.println("controller" + userCreateRequestDTO.getUsername());
        return  responseUtility.Get(userCreateRequestSevice.HandleUserCreateRequest(userCreateRequestDTO),HttpStatus.OK);
    }


}
