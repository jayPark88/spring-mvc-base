package com.jaypark8282.base.api.v1.user.controller;


import com.jaypark8282.base.api.v1.user.service.UserService;
import com.jaypark8282.core.dto.request.UserDto;
import com.jaypark8282.core.dto.request.UserUpdateRequestDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.UserEntity;
import com.jaypark8282.core.resonse.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_404;
import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_500;


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
@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping("/signUp")
    public CommonResponse<UserEntity> signup(
            @Valid @RequestBody UserDto userDto, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(FAIL_404.code(), messageSource.getMessage("http.status.bad.request", null, Locale.getDefault()), HttpStatus.BAD_REQUEST);
        }

        try {
            return new CommonResponse<>(userService.signUp(userDto));
        } catch (DataAccessException e) {
            log.info("signUp error {}", e.getMessage());
            throw new CustomException(FAIL_500.code(), messageSource.getMessage("user.signup.save.fail", null, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{userId}")
    public CommonResponse<UserEntity> modifyUserInfo(@PathVariable("userId") String userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return new CommonResponse<>(userService.updateUser(userId, userUpdateRequestDto));
    }
}