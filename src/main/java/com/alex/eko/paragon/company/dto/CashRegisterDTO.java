package com.alex.eko.paragon.company.dto;


import com.alex.eko.paragon.company.domain.enums.CashRegisterType;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.alex.eko.paragon.utils.abstraction.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Data Transfer Object representing a cash register in the system.")
public class CashRegisterDTO
        extends BaseDTO
        implements BaseProperty {



    String cashRegisterNumber;


    CashRegisterType cashRegisterType;



    Long lastSequentialPrintNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Boolean lastSequentialPrintNumberPlusOne;


    Long lastConsecutiveNumberFiscalReceipt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Boolean lastConsecutiveNumberFiscalReceiptPlusOne;

}
