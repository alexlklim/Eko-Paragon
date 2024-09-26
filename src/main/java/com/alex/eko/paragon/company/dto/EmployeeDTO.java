package com.alex.eko.paragon.company.dto;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.utils.abstraction.BaseDTO;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Data Transfer Object representing a Employee in the system.")
public class EmployeeDTO
        extends BaseDTO
        implements BaseProperty {


    Long belongToUserId;


    String firstName;


    String lastName;


    String email;

    String phoneNumber;


    String address;


    Long profileImageId;


    String cashierIdentification;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<CashRegisterDTO> cashRegisters;



    Map<Long, Boolean> cashRegistersMap;
}
