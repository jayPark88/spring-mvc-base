package com.jaypark8282.admin.api.v1.user.service;


import com.jaypark8282.core.dto.UserDto;
import com.jaypark8282.core.exception.CustomException;
import com.jaypark8282.core.jpa.entity.Authority;
import com.jaypark8282.core.jpa.entity.User;
import com.jaypark8282.core.jpa.repository.UserRepository;
import com.jaypark8282.core.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public User signUp(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUserName(userDto.getUserName()).isPresent()) {
            throw new CustomException(FAIL_2000.code(), messageSource.getMessage("error.2000", new String[]{userDto.getUserName()}, Locale.getDefault()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Authority authority =
                Authority.builder()
                        .authorityName("ROLE_USER")
                        .build();

        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    // 보통 jpa가 영속성 때문에 snapShot을 들고 다니고(일명 컨텍스트) dirtyChecking을 하는데 읽기 전용이라고 판단하면 이거 안함
    // 좋은점은 그래서 성능 향상 됨
    // 아래의 메서드는 userName param으로 모든 유저 정보 검색 가능하고
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userName) {
        return userRepository.findOneWithAuthoritiesByUserName(userName);
    }

    // 아래의 메서드는 현재 로그인된 사용자의 정보만 가져올 수 있다
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUserName().flatMap(userRepository::findOneWithAuthoritiesByUserName);
    }
}