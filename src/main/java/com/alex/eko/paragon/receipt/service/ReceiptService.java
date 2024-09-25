package com.alex.eko.paragon.receipt.service;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.receipt.mapper.ReceiptMapper;
import com.alex.eko.paragon.receipt.repo.ReceiptRepo;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReceiptService
        extends AbstractBaseService<Receipt, ReceiptDTO, ReceiptRepo, ReceiptMapper> {


    @Autowired
    public ReceiptService(ReceiptRepo repository, ReceiptMapper mapper) {
        super(repository, mapper, Receipt.class);
    }


    @Override
    protected void updateEntityFromDTO(Receipt company, ReceiptDTO receiptDTO) {

    }
}
