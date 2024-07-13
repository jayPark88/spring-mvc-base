package com.jaypark8282.core.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * com.jaypark8282.core.jpa.entity
 * ㄴ User
 *
 * <pre>
 * description :
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 *  parker, 1.0, 12/23/23  초기작성
 * </pre>
 *
 * @author parker
 * @version 1.0
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseInfoEntity {

    @Id
    @Column(name = "user_id", length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "user_name", length = 50, unique = true)
    private String userName;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "status", length = 50)
    private String status;
}