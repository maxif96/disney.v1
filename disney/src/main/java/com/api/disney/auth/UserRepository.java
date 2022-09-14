package com.api.disney.auth;


import com.api.disney.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(long id);

    UserEntity save(UserEntity user);

    boolean existsByEmail(String email);
    
}