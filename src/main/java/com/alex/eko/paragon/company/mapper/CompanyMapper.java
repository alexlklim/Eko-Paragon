package com.alex.eko.paragon.company.mapper;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.dto.CompanyDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import org.springframework.stereotype.Service;


@Service
public class CompanyMapper
        extends AbstractMapper<Company, CompanyDTO> {


    @Override
    protected void mapToDTO(CompanyDTO dto, Company entity) {

    }
}
