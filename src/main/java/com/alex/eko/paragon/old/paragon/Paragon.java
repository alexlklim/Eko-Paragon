package com.alex.eko.paragon.old.paragon;

import com.alex.eko.paragon.old.paragon.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paragons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Paragon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "uuid")
    String uuid;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "taxpayer_name")
    String taxpayerName;

    @Column(name = "sale_address")
    String saleAddress;

    @Column(name = "nip")
    String nip;

    @Column(name = "sequential_print_number")
    Long sequentialPrintNumber;

    @Column(name = "sales_date")
    LocalDateTime salesDate;


    @Column(name = "curr")
    @Enumerated(EnumType.STRING)
    Currency currency;

    @Column(name = "gross_price")
    Double grossPrice;

    @Column(name = "consecutive_number_fiscal_receipt")
    Long consecutiveNumberFiscalReceipt;

    @Column(name = "cash_register_number")
    String cashRegisterNumber;

    @Column(name = "cashier_identification")
    String cashierIdentification;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paragon_id")
    List<Product> products;


    @Override
    public String toString() {
        return "Paragon{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", userId=" + userId +
                ", taxpayerName='" + taxpayerName + '\'' +
                ", saleAddress='" + saleAddress + '\'' +
                ", nip='" + nip + '\'' +
                ", sequentialPrintNumber=" + sequentialPrintNumber +
                ", salesDate=" + salesDate +
                ", currency=" + currency +
                ", grossPrice=" + grossPrice +
                ", consecutiveNumberFiscalReceipt=" + consecutiveNumberFiscalReceipt +
                ", cashRegisterNumber='" + cashRegisterNumber + '\'' +
                ", cashierIdentification='" + cashierIdentification + '\'' +
                ", products=" + products +
                '}';
    }
}
