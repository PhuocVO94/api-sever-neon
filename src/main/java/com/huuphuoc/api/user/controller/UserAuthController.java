package com.huuphuoc.api.user.controller;

import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.common.utils.ResponseUtility;
import com.huuphuoc.api.security.JWTAuthDTO;
import com.huuphuoc.api.security.JWTGennerator;
import com.huuphuoc.api.user.dto.UserBodyDTO;
import com.huuphuoc.api.user.dto.UserLogInDTO;
import com.huuphuoc.api.user.service.UserAuthSeviceImp;
import com.huuphuoc.api.user.utils.UserApiConfigUrls;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfigUrls.URL_AUTH)
@RequiredArgsConstructor
public class UserAuthController {
    private  final AuthenticationManager authenticationManager;
    private final UserAuthSeviceImp userAuthSeviceImp;
    private final ResponseUtility responseUtility;
    private  final JWTGennerator jwtGennerator;

    @PostMapping(UserApiConfigUrls.URL_Register)
    public Object SavedRequest(@RequestBody @Valid UserBodyDTO userBodyDTO) {
        return responseUtility.Get(userAuthSeviceImp.RegistrationRequest(userBodyDTO), HttpStatus.OK);
    }



    @PostMapping(UserApiConfigUrls.URL_Login)
    public Object Login(@RequestBody UserLogInDTO userLogInDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogInDTO.getEmail(), userLogInDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String JWTToken = jwtGennerator.Gennerate(authentication);
            return responseUtility.Get(new JWTAuthDTO(JWTToken), HttpStatus.OK);
        } catch (DisabledException ex) {

            return responseUtility.Error(new IllegalStateException("Tài khoản chưa được kích hoạt. Vui lòng xác nhận email trước khi đăng nhập."), HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException ex) {
            return responseUtility.Error(new IllegalStateException("Email hoặc mật khẩu không đúng."), HttpStatus.UNAUTHORIZED);
        }

    }


}