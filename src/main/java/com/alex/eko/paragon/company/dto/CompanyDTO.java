package com.alex.eko.paragon.company.dto;


import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.alex.eko.paragon.utils.abstraction.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Data Transfer Object representing a company in the system.")
public class CompanyDTO
        extends BaseDTO
        implements BaseProperty {


    String companyName;

    String companyAddress;

    String companyEmail;

    String companyPhoneNumber;

    String companyWebsite;

    Long logoImageId;

    String taxpayerName;

    String saleAddress;

    String nip;

    Long ownerUserId;


}
