package com.huuphuoc.webBH.oder.controller;

import com.huuphuoc.webBH.common.utils.ResponseUtility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("oder")
public class OrderController {

    ResponseUtility responseUtility;
    @PostMapping("/save")
    public Object save() {
        return responseUtility.Get("Dang Lam Oder", HttpStatus.OK);

    }
}
