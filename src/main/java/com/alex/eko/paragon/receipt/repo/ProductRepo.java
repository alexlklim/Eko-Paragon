package com.alex.eko.paragon.receipt.repo;

import com.alex.eko.paragon.company.domain.Employee;
import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.utils.abstraction.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo
        extends BaseRepo<Product, Long> {


    @Query("SELECT s " +
            "FROM Product s " +
            "WHERE s.company.id = :companyId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<Product> findByCompany(
            @Param("companyId") Long companyId,
            @Param("deleted") Boolean deleted);



    @Query("SELECT s " +
            "FROM Product s " +
            "WHERE s.id IN :companyId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<Product> findByIds(
            @Param("companyId") List<Long> ids,
            @Param("deleted") Boolean deleted);
}
