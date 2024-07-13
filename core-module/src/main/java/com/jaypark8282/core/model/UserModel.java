package com.jaypark8282.core.model;

import com.jaypark8282.core.intf.ChangableToFromEntity;
import com.jaypark8282.core.jpa.entity.UserEntity;

public class UserModel implements ChangableToFromEntity<UserEntity> {
    private String userId;
    private String userName;
    private String phone;
    private String email;
    private String role;
    private String type;
    private String status;

    public UserModel(UserEntity user) {
        from(user);
    }

    @Override
    public UserEntity to() {

        return UserEntity.builder()
                .userId(userId)
                .userName(userName)
                .phone(phone)
                .email(email)
                .role(role)
                .type(type)
                .status(status)
                .build();
    }

    @Override
    public void from(UserEntity entity) {
        userId = entity.getUserId();
        userName = entity.getUserName();
        phone = entity.getPhone();
        email = entity.getEmail();
        role = entity.getRole();
        type = entity.getType();
        status = entity.getStatus();
    }
}
