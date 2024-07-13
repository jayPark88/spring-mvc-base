package com.jaypark8282.admin.api.v1.user.controller;


import com.jaypark8282.admin.api.v1.user.service.UserService;
import com.jaypark8282.core.dto.request.UserDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.UserEntity;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_404;


/**
 * com.parker.admin.api.v1.user.controller
 * ㄴ UserController
 *
 * <pre>
 * description :
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 *  parker, 1.0, 12/25/23  초기작성
 * </pre>
 *
 * @author parker
 * @version 1.0
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping("/signup")
    public CommonResponse<UserEntity> signup(
            @Valid @RequestBody UserDto userDto, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_404.code(), messageSource.getMessage("http.status.bad.request", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        }
        return new CommonResponse<>(userService.signUp(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public CommonResponse<UserEntity> getMyUserInfo() {
        return new CommonResponse<>(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CommonResponse<UserEntity> getUserInfo(@PathVariable String username) {
        return new CommonResponse<>(userService.getUserWithAuthorities(username).get());
    }
}