package com.alex.eko.paragon.company.repo;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.utils.abstraction.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepo
        extends BaseRepo<Company, Long> {


    @Query("SELECT s " +
            "FROM Company s " +
            "WHERE s.ownerUserId = :ownerUserId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    Optional<Company> findByOwnerIdAndDeleted(
            @Param("ownerUserId") Long ownerUserId,
            @Param("deleted") Boolean deleted);

}
