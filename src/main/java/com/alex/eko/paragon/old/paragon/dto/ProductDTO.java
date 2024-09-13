package com.alex.eko.paragon.old.paragon.dto;

import com.alex.eko.paragon.old.paragon.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

   String productName;

    Integer productQuantity;

    Double unitPrice;

    Category category;

    Double discount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double totalSum;
}
