package com.alex.eko.paragon.company.service;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.dto.CompanyDTO;
import com.alex.eko.paragon.company.mapper.CompanyMapper;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.image.ImageService;
import com.alex.eko.paragon.security.repo.SH;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.alex.eko.paragon.utils.functional.UpdateUtil.updateIfNotNull;


@Service
public class CompanyService
        extends AbstractBaseService<Company, CompanyDTO, CompanyRepo, CompanyMapper> {


    private final ImageService imageService;

    @Autowired
    public CompanyService(CompanyRepo repository, CompanyMapper mapper, ImageService imageService) {
        super(repository, mapper, Company.class);
        this.imageService = imageService;
    }


    @Override
    protected void updateEntityFromDTO(Company entity, CompanyDTO dto) {
        updateIfNotNull(dto.getCompanyName(), entity::setCompanyName);
        updateIfNotNull(dto.getCompanyAddress(), entity::setCompanyAddress);
        updateIfNotNull(dto.getCompanyEmail(), entity::setCompanyEmail);
        updateIfNotNull(dto.getCompanyPhoneNumber(), entity::setCompanyPhoneNumber);
        updateIfNotNull(dto.getCompanyWebsite(), entity::setCompanyWebsite);
        updateIfNotNull(dto.getTaxpayerName(), entity::setTaxpayerName);
        updateIfNotNull(dto.getSaleAddress(), entity::setSaleAddress);
        updateIfNotNull(dto.getNip(), entity::setNip);

        updateIfNotNull(dto.getDeleted(), entity::setDeleted);

        // if owner is not specified, set current user as owner, owner id can't be null
        if (dto.getOwnerUserId() == null) {
            entity.setOwnerUserId(SH.getUserId());
        }
        updateIfNotNull(dto.getOwnerUserId(), entity::setOwnerUserId);

        //   update image id
        if (dto.getLogoImageId() != null) {
            entity.setLogoImage(imageService.getFileById(dto.getLogoImageId()));
        }

    }


    @Override
    @SneakyThrows
    public CompanyDTO update(CompanyDTO companyDTO) {
        Optional<Company> company = repository.findByOwnerIdAndDeleted(SH.getUserId(), false);
        if (company.isPresent()) {
            updateCompany(company.get(), companyDTO);
        } else {
            Company entity = new Company();
            entity.setCreated(LocalDateTime.now());
            entity.setCreatedBy(SH.getUserId());
            entity.setDeleted(false);
            updateCompany(entity, companyDTO);
        }
        return mapper.toDTO(repository.findByOwnerIdAndDeleted(SH.getUserId(), false).get());
    }

    @SneakyThrows
    private void updateCompany(Company entity, CompanyDTO dto) {
        updateEntityFromDTO(entity, dto);
        repository.save(entity);
    }



    @SneakyThrows
    public CompanyDTO getMyCompany() {
        return mapper.toDTO(repository.findByOwnerIdAndDeleted(SH.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Company not found")));
    }
}
