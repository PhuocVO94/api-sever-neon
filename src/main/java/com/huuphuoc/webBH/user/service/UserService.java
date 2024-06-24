package com.huuphuoc.webBH.user.service;

import com.huuphuoc.webBH.common.modelservice.GenericService;
import com.huuphuoc.webBH.user.dto.UserDTO;
import com.huuphuoc.webBH.user.model.User;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {

}
