package com.doublexman.microservice.sample.repository;


import com.doublexman.microservice.sample.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
