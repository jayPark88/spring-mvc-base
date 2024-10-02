package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.OrderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long> {
}
