package com.kay.controller;

import com.kay.common.ApiErrorResponse;
import com.kay.security.properties.SecurityConstants;
import com.kay.service.AuthService;
import com.kay.util.DateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author LiuKay
 * @since 2019/12/5
 */
@RestController
@Api(value = "验证相关API")
public class LoginController {

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(SecurityConstants.VERIFICATION_CODE_URL)
    @ApiOperation(value = "获取手机验证码")
    public void sendSmsCode(@RequestParam String mobile) {
        authService.sendVerificationCode(mobile);
    }


    @GetMapping(SecurityConstants.LOGIN_REQUIRE)
    public ApiErrorResponse requireLogin(HttpServletRequest request) {
        return ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .errorCode(HttpStatus.UNAUTHORIZED.toString())
                .message("Need Login.")
                .detail("Require Authentication, Please Login First.")
                .timestamp(DateTimeUtils.getTimestampAsString())
                .path(request.getRequestURI())
                .build();
    }
}
