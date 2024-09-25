package com.alex.eko.paragon.company.domain;


import com.alex.eko.paragon.image.Image;
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
@Table(name = "companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company
        extends BaseEntity
        implements BaseProperty {


    @Column(name = "company_name")
    String companyName;

    @Column(name = "company_address")
    String companyAddress;

    @Column(name = "company_email")
    String companyEmail;

    @Column(name = "company_phone_number")
    String companyPhoneNumber;

    @Column(name = "company_website")
    String companyWebsite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    Image logoImage;




    @Column(name = "taxpayer_name")
    String taxpayerName;

    @Column(name = "sale_address")
    String saleAddress;

    @Column(name = "nip")
    String nip;


    @Column(name = "owner_user_id")
    Long ownerUserId;

}
