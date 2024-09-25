package com.alex.eko.paragon.company.mapper;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterMapper
        extends AbstractMapper<CashRegister, CashRegisterDTO> {
    @Override
    protected void mapToDTO(CashRegisterDTO dto, CashRegister entity) {

    }
}
