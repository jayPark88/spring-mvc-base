package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * com.parker.jpa.repository
 * ㄴ UserRepository
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
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")// 쿼리 수행될때 Lazy 조회가 아니고 Eager 조회로 가져온다.
    Optional<User> findOneWithAuthoritiesByUserName(String userName);
}