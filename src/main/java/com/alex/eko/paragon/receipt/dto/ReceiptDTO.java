package com.alex.eko.paragon.receipt.dto;


import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.alex.eko.paragon.utils.abstraction.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Data Transfer Object representing a company in the system.")
public class ReceiptDTO
        extends BaseDTO
        implements BaseProperty {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long userId;

    String taxpayerName;

    String saleAddress;

    String nip;

    Long sequentialPrintNumber;

    LocalDateTime salesDate;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxA;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxB;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxC;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxA_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxB_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxC_PTU;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double sumPTU;

    String currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double totalDiscount;

    Double grossPrice;

    Long consecutiveNumberFiscalReceipt;

    String cashRegisterNumber;

    String cashierIdentification;

    List<ProductDTO> productDTOs;



}
