package com.alex.eko.paragon.company.mapper;

import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.domain.Employee;
import com.alex.eko.paragon.company.dto.EmployeeDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeMapper
        extends AbstractMapper<Employee, EmployeeDTO> {


   private final CashRegisterMapper cashRegisterMapper;

    @Override
    protected void mapToDTO(EmployeeDTO dto, Employee entity) {
        dto.setCreated(entity.getCreated());
        dto.setBelongToUserId(entity.getBelongToUserId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setProfileImageId(entity.getProfileImage() != null ? entity.getProfileImage().getId() : null);
        dto.setCashierIdentification(entity.getCashierIdentification());
        dto.setCashRegisters(cashRegisterMapper.toDTOs(entity.getCashRegisters()));
    }
}
