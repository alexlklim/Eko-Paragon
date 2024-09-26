package com.alex.eko.paragon.company.repo;

import com.alex.eko.paragon.company.domain.Employee;
import com.alex.eko.paragon.utils.abstraction.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo
        extends BaseRepo<Employee, Long> {
    @Query("SELECT s " +
            "FROM Employee s " +
            "WHERE s.company.id = :companyId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<Employee> findByCompany(
            @Param("companyId") Long companyId,
            @Param("deleted") Boolean deleted);

}
