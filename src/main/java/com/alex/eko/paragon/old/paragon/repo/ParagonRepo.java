package com.alex.eko.paragon.old.paragon.repo;

import com.alex.eko.paragon.old.paragon.Paragon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParagonRepo extends JpaRepository<Paragon, Long> {


    @Query("SELECT p FROM Paragon p WHERE p.userId = :userId")
    List<Paragon> getParagonsByUserId(@Param("userId") Long userId);

    Optional<Paragon> findParagonByUuid(String uuid);
}
