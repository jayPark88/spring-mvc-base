package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.OrderInfoHistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderInfoHistEntity, Long> {
}
