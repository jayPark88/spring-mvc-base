package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface UserRepository extends JpaRepository<UserEntity, String> {
}