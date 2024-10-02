package com.jaypark8282.core.util.security;

import com.jaypark8282.core.dto.request.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * com.parker.admin.util
 * ㄴ SecurityUtil
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
public class SecurityUtil {

    // 인스턴스 생성 못하게 하려고 only static 전용
    private SecurityUtil() {
    }

    public static Optional<String> getCurrentUserName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (ObjectUtils.isEmpty(authentication)) {
            log.debug("Security Context에 인증정보가 없습니다.");
            return Optional.empty();
        }

        // principal은 현재 사용자라는 의미
        String userName = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userName = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userName = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(userName);
    }
}