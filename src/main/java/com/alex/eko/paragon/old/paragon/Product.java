package com.alex.eko.paragon.old.paragon;


import com.alex.eko.paragon.old.paragon.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product")
    String product;

    @Enumerated(EnumType.STRING)
    Category category;

    @Column(name = "amount")
    Integer amount;

    @Column(name = "unit_price")
    Double unitPrice;

    @Column(name = "discount")
    Double discount;

    @ManyToOne @JoinColumn(name = "paragon_id")
    Paragon paragon;
}
