package com.jaypark8282.core.jpa.repository;


import com.jaypark8282.core.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<User, Long> {
}
