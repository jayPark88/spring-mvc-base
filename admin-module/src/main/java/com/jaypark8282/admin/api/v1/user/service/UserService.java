package com.jaypark8282.admin.api.v1.user.service;


import com.jaypark8282.core.dto.request.UserDto;
import com.jaypark8282.core.enums.Role;
import com.jaypark8282.core.enums.UserStatus;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.UserEntity;
import com.jaypark8282.core.jpa.repository.UserRepository;
import com.jaypark8282.core.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

import static com.jaypark8282.core.exception.enums.ResponseErrorCode.FAIL_2000;


/**
 * com.parker.admin.api.v1.login.service
 * ㄴ UserService
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
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Transactional
    public UserEntity signUp(UserDto userDto) {
        if (userRepository.findById(userDto.getUserId()).isPresent()) {
            throw new CustomException(FAIL_2000.code(), messageSource.getMessage("error.2000", new String[]{userDto.getUserName()}, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .role(userRepository.count() > 0 ? Role.ROLE_MASTER.code() : Role.ROLE_USER.code())
                .type(userDto.getType())
                .status(UserStatus.ACTIVATED.code())
                .createId(userDto.getUserId())
                .modifiedId(userDto.getUserId())
                .build();

        return userRepository.save(userEntity);
    }
}