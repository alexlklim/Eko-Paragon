package com.alex.eko.paragon.old.paragon.service;

import com.alex.eko.paragon.old.paragon.Product;
import com.alex.eko.paragon.old.paragon.dto.ProductDTO;
import com.alex.eko.paragon.old.paragon.enums.Category;

import java.util.List;

public class ProductMapper {
    public static List<ProductDTO> toDTOs(List<Product> products){
        return products.stream().map(ProductMapper::toDTO).toList();
    }
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .productName(product.getProduct())
                .productQuantity(product.getAmount())
                .unitPrice(product.getUnitPrice())
                .category(product.getCategory())
                .discount(product.getDiscount())
                .totalSum(calculateTotalSum(product.getAmount(), product.getUnitPrice(), product.getDiscount()))
                .build();
    }

    public static List<Product> toEntities(List<ProductDTO> DTOs){
        return DTOs.stream().map(ProductMapper::toEntity).toList();
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setProduct(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setAmount(dto.getProductQuantity());
        product.setUnitPrice(dto.getUnitPrice());
        product.setDiscount(dto.getDiscount());
        return product;
    }


    private static Double calculateTotalSum(Integer amount, Double unitPrice, Double discount) {
        double sum = amount * unitPrice;
        if (discount != null && discount > 0) {
            sum -= discount;
        }
        return sum;
    }




    public static Double sumTotalSumsForCategory(List<ProductDTO> products, Category category) {
        return products.stream()
                .filter(product -> category.equals(product.getCategory()))
                .mapToDouble(ProductDTO::getTotalSum)
                .sum();
    }



    public static Double sumTotalDiscounts(List<ProductDTO> products) {
        return products.stream()
                .mapToDouble(product -> product.getDiscount() != null ? product.getDiscount() : 0.0)
                .sum();
    }

}
