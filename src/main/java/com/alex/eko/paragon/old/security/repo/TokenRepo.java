package com.alex.eko.paragon.old.security.repo;

import com.alex.eko.paragon.old.security.domain.Token;
import com.alex.eko.paragon.old.security.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepo extends JpaRepository<Token, Long> {



  @Modifying
  @Transactional
  @Query("DELETE FROM Token t WHERE t.user = :user")
  void deleteAllByUser(@Param("user") User user);


  Optional<Token> findByUserAndToken(User user, UUID token);

  Optional<Token> findByToken(UUID token);
}
