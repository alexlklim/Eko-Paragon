package com.alex.eko.paragon.company.service;


import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.company.mapper.CashRegisterMapper;
import com.alex.eko.paragon.company.repo.CashRegisterRepo;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterService
        extends AbstractBaseService<CashRegister, CashRegisterDTO, CashRegisterRepo, CashRegisterMapper> {



    @Autowired
    public CashRegisterService(CashRegisterRepo repository, CashRegisterMapper mapper) {
        super(repository, mapper, CashRegister.class);
    }


    @Override
    protected void updateEntityFromDTO(CashRegister cashRegister, CashRegisterDTO cashRegisterDTO) {

    }
}
