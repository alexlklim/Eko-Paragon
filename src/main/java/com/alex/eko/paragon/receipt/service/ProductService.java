package com.alex.eko.paragon.receipt.service;

import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.mapper.ProductMapper;
import com.alex.eko.paragon.receipt.repo.ProductRepo;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService
        extends AbstractBaseService<Product, ProductDTO, ProductRepo, ProductMapper> {


    @Autowired
    public ProductService(ProductRepo repository, ProductMapper mapper) {
        super(repository, mapper, Product.class);
    }


    @Override
    protected void updateEntityFromDTO(Product company, ProductDTO ProductDTO) {

    }
}
