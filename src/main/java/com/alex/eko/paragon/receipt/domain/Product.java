package com.alex.eko.paragon.receipt.domain;


import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.receipt.domain.enums.Category;
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
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product
        extends BaseEntity
        implements BaseProperty {

    @Column(name = "product_title")
    String productTitle;

    @Enumerated(EnumType.STRING)
    Category category;

    @Column(name = "amount")
    Integer amount;

    @Column(name = "unit_price")
    Double unitPrice;

    @Column(name = "discount")
    Double discount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;



}
