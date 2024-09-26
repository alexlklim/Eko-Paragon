package com.alex.eko.paragon.security.repo;

import com.alex.eko.paragon.security.domain.Role;
import com.alex.eko.paragon.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id  = ?1")
    User getUser(Long id);


    boolean existsByEmail(String email);


    Optional<User> getUserByEmail(String username);

}
