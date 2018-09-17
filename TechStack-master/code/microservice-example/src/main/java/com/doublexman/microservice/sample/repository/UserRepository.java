package com.doublexman.microservice.sample.repository;
import com.doublexman.microservice.sample.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUserId(String userId);

    Page<User> findAll(Pageable page);
}
