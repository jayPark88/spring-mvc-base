package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
