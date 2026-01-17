package com.huuphuoc.webBH.user.controller;


import com.huuphuoc.webBH.common.enums.Roles;
import com.huuphuoc.webBH.common.enums.Status;
import com.huuphuoc.webBH.common.utils.ResponseUtility;
import com.huuphuoc.webBH.common.Util.UserUtilconfig;
import com.huuphuoc.webBH.user.dto.UserBodyDTO;
import com.huuphuoc.webBH.user.model.User;
import com.huuphuoc.webBH.user.repository.IUserRepository;
import com.huuphuoc.webBH.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/*
 * Rest Controller 1 Bản nâng cấp của controller và cũng dùng đánh dấu Trang Controller
 *
 * */
@RestController
@RequestMapping(UserUtilconfig.USER)

public class UserController {



    ResponseUtility responseUtility;
    UserService userService;
    IUserRepository iUserRepository;


    public UserController(UserService userService, ResponseUtility responseUtility, IUserRepository iUserRepository) {
        this.userService = userService;
        this.responseUtility = responseUtility;
        this.iUserRepository = iUserRepository;
    }



    @PostMapping(UserUtilconfig.USER_SAVE)
    public Object save(@RequestBody @Valid UserBodyDTO userBodyDTO) {
        return responseUtility.Get(userService.save(new User(userBodyDTO.getUsername(), userBodyDTO.getPassword(),
                userBodyDTO.getEmail())), HttpStatus.OK);
    }


    @GetMapping(UserUtilconfig.USER_GET_ALL)
    public Object findAll() {
        return responseUtility.Get(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Object findUserByID(@PathVariable("id")UUID uuid) {
        return responseUtility.Get(userService.findByID(uuid), HttpStatus.OK);
    }

}
