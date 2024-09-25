package com.alex.eko.paragon.company.service;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.dto.CompanyDTO;
import com.alex.eko.paragon.company.mapper.CompanyMapper;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyService
        extends AbstractBaseService<Company, CompanyDTO, CompanyRepo, CompanyMapper> {


    @Autowired
    public CompanyService(CompanyRepo repository, CompanyMapper mapper) {
        super(repository, mapper, Company.class);
    }


    @Override
    protected void updateEntityFromDTO(Company company, CompanyDTO companyDTO) {

    }
}
