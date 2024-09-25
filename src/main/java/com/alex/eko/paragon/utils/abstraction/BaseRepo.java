package com.alex.eko.paragon.utils.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepo<T extends BaseProperty, ID> extends JpaRepository<T, ID> {

    @Query("SELECT s " +
            "FROM #{#entityName} s " +
            "WHERE s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<T> findAllByDeleted(@Param("deleted") Boolean deleted);

    @Query("SELECT s " +
            "FROM #{#entityName} s " +
            "WHERE s.deleted = :deleted AND s.id = :id " +
            "ORDER BY s.created DESC")
    Optional<T> findByIdAndDeleted(@Param("id") Long id, @Param("deleted") Boolean deleted);

}