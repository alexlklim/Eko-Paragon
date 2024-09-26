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
        dto.setCreated(entity.getCreated());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCompanyAddress(entity.getCompanyAddress());
        dto.setCompanyEmail(entity.getCompanyEmail());
        dto.setCompanyPhoneNumber(entity.getCompanyPhoneNumber());
        dto.setCompanyWebsite(entity.getCompanyWebsite());

        dto.setLogoImageId(entity.getLogoImage() != null ? entity.getLogoImage().getId() : null);

        dto.setTaxpayerName(entity.getTaxpayerName());
        dto.setSaleAddress(entity.getSaleAddress());
        dto.setNip(entity.getNip());
        dto.setOwnerUserId(entity.getOwnerUserId());
    }

}
