package com.alex.eko.paragon.company.service;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.company.mapper.CashRegisterMapper;
import com.alex.eko.paragon.company.repo.CashRegisterRepo;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.security.repo.SH;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import com.alex.eko.paragon.utils.exceptions.errors.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

import static com.alex.eko.paragon.utils.functional.UpdateUtil.updateIfNotNull;

@Service
public class CashRegisterService
        extends AbstractBaseService<CashRegister, CashRegisterDTO, CashRegisterRepo, CashRegisterMapper> {


    private final CompanyRepo companyRepo;

    @Autowired
    public CashRegisterService(CashRegisterRepo repository, CashRegisterMapper mapper, CompanyRepo companyRepo) {
        super(repository, mapper, CashRegister.class);
        this.companyRepo = companyRepo;
    }


    @Override
    protected void updateEntityFromDTO(CashRegister entity, CashRegisterDTO dto) {
        updateIfNotNull(dto.getCashRegisterNumber(), entity::setCashRegisterNumber);
        updateIfNotNull(dto.getCashRegisterType(), entity::setCashRegisterType);
        updateIfNotNull(dto.getLastSequentialPrintNumber(), entity::setLastSequentialPrintNumber);
        updateIfNotNull(dto.getLastConsecutiveNumberFiscalReceipt(), entity::setLastConsecutiveNumberFiscalReceipt);

        // set company id
        // company id can't be changed
        entity.setCompany(companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Company not found")));


        // add possibility to ++ lastSequentialPrintNumber
        // add possibility to ++ lastConsecutiveNumberFiscalReceipt
        if (dto.getLastConsecutiveNumberFiscalReceiptPlusOne() != null && dto.getLastConsecutiveNumberFiscalReceiptPlusOne()) {
            entity.setLastConsecutiveNumberFiscalReceipt(entity.getLastConsecutiveNumberFiscalReceipt() + 1);
        }
        if (dto.getLastSequentialPrintNumberPlusOne() != null && dto.getLastSequentialPrintNumberPlusOne()) {
            entity.setLastSequentialPrintNumber(entity.getLastSequentialPrintNumber() + 1);
        }


    }


    @Override
    @SneakyThrows
    protected void isUserHaveOwnerPermission(CashRegister entity) {
        if (entity.getCompany().getOwnerUserId() != SH.getUserId()) {
            throw new ResourceNotFoundException("Entity doesn't belong to the current user");
        }
    }



    @Override
    @SneakyThrows
    public CashRegisterDTO getDTOById(Long id) {
        Company company = companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        CashRegister cashRegister = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cash register not found"));
        if (cashRegister.getCompany().getId().equals(company.getId())) {
            return mapper.toDTO(cashRegister);
        } else {
            throw new RuntimeException("Cash register not found");
        }
    }




    @SneakyThrows
    public List<CashRegisterDTO> getAllByCompany(Boolean deleted) {
        Company company = companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), deleted)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapper.toDTOs(repository.findByCompany(company.getId(), deleted));
    }
}
