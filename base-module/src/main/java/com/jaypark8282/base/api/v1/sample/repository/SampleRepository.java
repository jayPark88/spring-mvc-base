package com.jaypark8282.base.api.v1.sample.repository;

import com.jaypark8282.base.api.v1.sample.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository  extends JpaRepository<SampleEntity, Long> {
}
