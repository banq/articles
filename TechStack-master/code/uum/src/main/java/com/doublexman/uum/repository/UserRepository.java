package com.doublexman.uum.repository;
import com.doublexman.uum.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUserId(String userId);

    Page<User> findAll(Pageable page);
}
