package com.alex.eko.paragon.company.mapper;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.domain.enums.CashRegisterType;
import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import org.springframework.stereotype.Service;

import static com.alex.eko.paragon.utils.functional.UpdateUtil.updateIfNotNull;

@Service
public class CashRegisterMapper
        extends AbstractMapper<CashRegister, CashRegisterDTO> {
    @Override
    protected void mapToDTO(CashRegisterDTO dto, CashRegister entity) {
        dto.setCreated(entity.getCreated());
        dto.setCashRegisterNumber(entity.getCashRegisterNumber());
        dto.setCashRegisterType(entity.getCashRegisterType());
        dto.setLastSequentialPrintNumber(entity.getLastSequentialPrintNumber());
        dto.setLastConsecutiveNumberFiscalReceipt(entity.getLastConsecutiveNumberFiscalReceipt());
    }

}
