package com.huuphuoc.api.user.controller;

import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.common.exception.DataNotFoundException;
import com.huuphuoc.api.common.exception.GlobalExceptionHandler;
import com.huuphuoc.api.common.utils.ResponseUtility;
import com.huuphuoc.api.redis.service.RedisService;
import com.huuphuoc.api.redis.service.RefreshTokenService;
import com.huuphuoc.api.security.JWTAuthDTO;
import com.huuphuoc.api.security.JWTGenerator;
import com.huuphuoc.api.user.dto.TokenResfeshRequest;
import com.huuphuoc.api.user.dto.UserBodyDTO;
import com.huuphuoc.api.user.dto.UserLogInDTO;
import com.huuphuoc.api.user.service.UserAuthSeviceImp;
import com.huuphuoc.api.user.utils.UserApiConfigUrls;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ApiConfigUrls.URL_AUTH)
@RequiredArgsConstructor
public class UserAuthController {
    private  final AuthenticationManager authenticationManager;
    private final UserAuthSeviceImp userAuthSeviceImp;
    private final ResponseUtility responseUtility;
    private final JWTGenerator jwtGenerator;
    private  final RedisService redisService;
    private  final RefreshTokenService refreshTokenService;





    @PostMapping(UserApiConfigUrls.URL_Register)
    public Object SavedRequest(@RequestBody @Valid UserBodyDTO userBodyDTO) throws DataNotFoundException, Exception {
        return responseUtility.Get(userAuthSeviceImp.RegistrationRequest(userBodyDTO), HttpStatus.OK);
    }



    @PostMapping(UserApiConfigUrls.URL_Login)
    public Object Login(@RequestBody UserLogInDTO userLogInDTO) throws DataNotFoundException, Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogInDTO.getEmail(),(userLogInDTO.getPassword()))
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtGenerator.Gennerate(userLogInDTO.getEmail());
        String resfeshToken =  refreshTokenService.createRefreshToken(authentication);


        return responseUtility.Get(new JWTAuthDTO(accessToken, resfeshToken), HttpStatus.OK);


    }


    @PostMapping(UserApiConfigUrls.URL_Logout)
    public Object Logout(@RequestHeader String token) throws ParseException , Exception, DataNotFoundException{
        return responseUtility.Get(redisService.LogoutService(token),HttpStatus.OK);
    }




    @PostMapping(UserApiConfigUrls.URL_RefeshToken)
    public  Object Refresh(@RequestBody TokenResfeshRequest tokenResfeshRequest)throws Exception, DataNotFoundException {
        boolean valid = refreshTokenService.validateRefreshToken(tokenResfeshRequest.getEmail(), tokenResfeshRequest.getResfeshToken());

        if (valid) {
            String newAccessToken = jwtGenerator.Gennerate(tokenResfeshRequest.getEmail());
            Map<String, String> tokens = new HashMap<>();
            tokens.put("NewAccessToken", newAccessToken);
            tokens.put("Refresh", tokenResfeshRequest.getResfeshToken());
            return responseUtility.Get(tokens, HttpStatus.OK);

        }
        return responseUtility.Get("HttpStatus.UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/reset-password/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable("email") @Valid String email) throws DataNotFoundException, Exception{
        return responseUtility.Get(userAuthSeviceImp.ResetPassword(email),HttpStatus.OK);
    }

}