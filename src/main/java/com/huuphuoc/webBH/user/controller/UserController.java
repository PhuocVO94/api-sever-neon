package com.huuphuoc.webBH.user.controller;


import com.huuphuoc.webBH.common.url.ResponseUtility;
import com.huuphuoc.webBH.user.Util.UserUtilconfig;
import com.huuphuoc.webBH.user.dto.UserBodyDTO;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/*
 * Rest Controller 1 Bản nâng cấp của controller và cũng dùng đánh dấu Trang Controller
 *
 * */
@RestController
@RequestMapping(UserUtilconfig.USER)

public class UserController {


    ResponseUtility responseUtility;
    UserService userService;


    public UserController(UserService userService, ResponseUtility responseUtility) {
        this.userService = userService;
        this.responseUtility = responseUtility;
    }



    /*    username,password,phoneNumber,email
     */

    @PostMapping(UserUtilconfig.USER_SAVE)
    public Object save(@RequestBody @Valid UserBodyDTO userBodyDTO) {
        return responseUtility.Get(userService.save(new User(userBodyDTO.getUsername(), userBodyDTO.getPassword(),
                userBodyDTO.getPhoneNumber(),
                userBodyDTO.getEmail())), HttpStatus.OK);
    }


    @GetMapping(UserUtilconfig.USER_GET_ALL)
    public Object findAll() {
        return responseUtility.Get(userService.findAll(), HttpStatus.OK);
    }

}
