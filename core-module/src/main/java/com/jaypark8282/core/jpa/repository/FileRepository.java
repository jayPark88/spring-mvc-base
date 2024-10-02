package com.jaypark8282.core.jpa.repository;

import com.jaypark8282.core.jpa.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
