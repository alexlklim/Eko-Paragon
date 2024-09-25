package com.alex.eko.paragon.receipt.mapper;

import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import org.springframework.stereotype.Service;


@Service
public class ProductMapper
        extends AbstractMapper<Product, ProductDTO> {


    @Override
    protected void mapToDTO(ProductDTO dto, Product entity) {

    }
}
