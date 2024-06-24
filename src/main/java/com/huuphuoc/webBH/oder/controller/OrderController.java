package com.huuphuoc.webBH.oder.controller;

import com.huuphuoc.webBH.common.url.ResponseUtility;
import com.huuphuoc.webBH.user.service.UserSeviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oder")
public class OrderController {


    UserSeviceimpl userSevice;
    ResponseUtility responseUtility;


    public OrderController(UserSeviceimpl userSevice) {
        this.userSevice = userSevice;
    }

    //
    @PostMapping("/save")
    public Object save() {

        return responseUtility.Get("Dang Lam Oder", HttpStatus.OK);

    }
}
