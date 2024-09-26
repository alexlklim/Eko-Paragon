package com.alex.eko.paragon.receipt.mapper;

import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.domain.enums.Category;
import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;


@Service
public class ProductMapper
        extends AbstractMapper<Product, ProductDTO> {


    @Override
    protected void mapToDTO(ProductDTO dto, Product entity) {
        dto.setCreated(entity.getCreated());
        dto.setProductName(entity.getProductTitle());
        dto.setProductQuantity(entity.getAmount());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setCategory(entity.getCategory());
        dto.setDiscount(entity.getDiscount());
        double totalSum = entity.getAmount() * entity.getUnitPrice() - entity.getDiscount();
        dto.setTotalSum(totalSum);
    }
}
