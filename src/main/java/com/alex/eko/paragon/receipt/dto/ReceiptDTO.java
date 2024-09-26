package com.alex.eko.paragon.receipt.dto;

import com.alex.eko.paragon.receipt.domain.enums.Currency;
import com.alex.eko.paragon.utils.abstraction.BaseDTO;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    Long receiverId;

    String taxpayerName;

    String saleAddress;

    String nip;

    Long sequentialPrintNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime salesDate;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxA;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxB;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxC;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double salesTaxA_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double salesTaxB_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double salesTaxC_PTU;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double sumPTU;

    Currency currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double totalDiscount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.##")
    Double grossPrice;

    Long consecutiveNumberFiscalReceipt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String cashRegisterNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String cashierIdentification;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProductDTO> productDTOs;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Long> productIds;






    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long cashRegisterId;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Long employeeId;
}
