package com.alex.eko.paragon.company.domain;


import com.alex.eko.paragon.company.domain.enums.CashRegisterType;
import com.alex.eko.paragon.utils.abstraction.BaseEntity;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "cash_registers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashRegister
        extends BaseEntity
        implements BaseProperty {

    @Column(name = "cash_register_number")
    String cashRegisterNumber;


    @Enumerated(EnumType.STRING)
    CashRegisterType cashRegisterType;


    @Column(name = "last_sequential_print_number")
    Long lastSequentialPrintNumber;


    @Column(name = "last_consecutive_number_fiscal_receipt")
    Long lastConsecutiveNumberFiscalReceipt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

}
