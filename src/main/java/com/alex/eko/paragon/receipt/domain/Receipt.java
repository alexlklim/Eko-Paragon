package com.alex.eko.paragon.receipt.domain;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.domain.Employee;
import com.alex.eko.paragon.receipt.domain.enums.Currency;
import com.alex.eko.paragon.utils.abstraction.BaseEntity;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "receipts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Receipt
        extends BaseEntity
        implements BaseProperty {



    @Column(name = "uuid")  // unique identifier of receipt
    String uuid;

    @Column(name = "receiver_id")  // user who get this receipt
    Long receiverId;





    @Column(name = "sequential_print_number")
    Long sequentialPrintNumber;


    @Column(name = "consecutive_number_fiscal_receipt")
    Long consecutiveNumberFiscalReceipt;


    @Column(name = "sales_date")
    LocalDateTime salesDate;


    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

//    @Column(name = "gross_price")
//    Double grossPrice;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_register_id")
    CashRegister cashRegister;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    Employee employee;



    @ManyToMany
    @JoinTable(
            name = "receipts_products",
            joinColumns = @JoinColumn(name = "receipt_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;


}
