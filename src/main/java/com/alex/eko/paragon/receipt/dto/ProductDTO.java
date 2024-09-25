package com.alex.eko.paragon.receipt.dto;


import com.alex.eko.paragon.receipt.domain.enums.Category;
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
@Schema(description = "Data Transfer Object representing a company in the system.")
public class ProductDTO
        extends BaseDTO
        implements BaseProperty {

    String productName;

    Integer productQuantity;

    Double unitPrice;

    Category category;

    Double discount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double totalSum;



}
