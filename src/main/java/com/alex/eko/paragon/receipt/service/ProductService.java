package com.alex.eko.paragon.receipt.service;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.enums.Category;
import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.mapper.ProductMapper;
import com.alex.eko.paragon.receipt.repo.ProductRepo;
import com.alex.eko.paragon.security.repo.SH;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import jakarta.persistence.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService
        extends AbstractBaseService<Product, ProductDTO, ProductRepo, ProductMapper> {


    private final CompanyRepo companyRepo;
    @Autowired
    public ProductService(ProductRepo repository, ProductMapper mapper, CompanyRepo companyRepo) {
        super(repository, mapper, Product.class);
        this.companyRepo = companyRepo;
    }


    @Override
    protected void updateEntityFromDTO(Product entity, ProductDTO dto) {

        entity.setProductTitle(dto.getProductName());
        entity.setAmount(dto.getProductQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setCategory(dto.getCategory());
        entity.setDiscount(dto.getDiscount());

        // set the company current user belongs to
        entity.setCompany(companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Company not found")));

    }

    @SneakyThrows
    public List<ProductDTO> getAllByCompany(Boolean deleted) {
        Company company = companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), deleted)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapper.toDTOs(repository.findByCompany(company.getId(), deleted));

    }
}
