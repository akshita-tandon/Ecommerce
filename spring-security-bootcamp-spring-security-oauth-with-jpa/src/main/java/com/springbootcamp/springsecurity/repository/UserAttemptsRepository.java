package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.UserAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttemptsRepository extends CrudRepository<UserAttempts,Long> {

    UserAttempts findByUsername(String username);
}
