package com.alex.eko.paragon.receipt.repo;

import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.utils.abstraction.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceiptRepo
        extends BaseRepo<Receipt, Long> {

    @Query("SELECT s " +
            "FROM Receipt s " +
            "WHERE s.cashRegister.company.id = :companyId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<Receipt> findByCompany(
                    @Param("companyId") Long companyId,
                    @Param("deleted") Boolean deleted);


    @Query("SELECT s " +
            "FROM Receipt s " +
            "WHERE s.receiverId = :receiverId AND s.deleted = :deleted " +
            "ORDER BY s.created DESC")
    List<Receipt> findByReceiver(
            @Param("receiverId") Long receiverId,
            @Param("deleted") Boolean deleted);
}
