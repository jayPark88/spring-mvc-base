package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.OrderInfoHistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoHistoryRepository extends JpaRepository<OrderInfoHistEntity, Long> {
}
