package com.jaypark8282.core.jpa.listener;

import com.jaypark8282.core.jpa.entity.BaseInfoEntity;
import com.jaypark8282.core.util.security.SecurityUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Optional;

public class BaseEntityListener {
    @PrePersist
    void onSave(BaseInfoEntity entity) {
        Optional<String> userId = SecurityUtil.getCurrentUserName();
        if (userId.isPresent()) {
            entity.setCreateId(userId.get());
            entity.setModifiedId(userId.get());
        }
    }

    @PreUpdate
    void onUpdate(BaseInfoEntity entity) {
        Optional<String> userId = SecurityUtil.getCurrentUserName();

        if (userId.isPresent()) {
            entity.setModifiedId(userId.get());
        }
    }
}
