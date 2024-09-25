package com.alex.eko.paragon.receipt.mapper;

import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import org.springframework.stereotype.Service;


@Service
public class ReceiptMapper
        extends AbstractMapper<Receipt, ReceiptDTO> {


    @Override
    protected void mapToDTO(ReceiptDTO dto, Receipt entity) {

    }
}
