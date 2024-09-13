package com.alex.eko.paragon.old.paragon.service;

import com.alex.eko.paragon.old.paragon.Paragon;
import com.alex.eko.paragon.old.paragon.Product;
import com.alex.eko.paragon.old.paragon.dto.ParagonDTO;
import com.alex.eko.paragon.old.paragon.dto.ProductDTO;
import com.alex.eko.paragon.old.paragon.enums.Category;
import com.alex.eko.paragon.old.paragon.enums.Currency;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParagonMapper {


    public static List<ParagonDTO> toDTOs(List<Paragon> paragons){
        return paragons.stream().map(ParagonMapper::toDTO).toList();
    }

    public static ParagonDTO toDTO(Paragon paragon) {
        ParagonDTO dto = ParagonDTO.builder()
                .uuid(paragon.getUuid())
                .taxpayerName(paragon.getTaxpayerName())
                .saleAddress(paragon.getSaleAddress())
                .nip(paragon.getNip())
                .sequentialPrintNumber(paragon.getSequentialPrintNumber())
                .salesDate(paragon.getSalesDate())
                .currency(paragon.getCurrency().name())
                .grossPrice(paragon.getGrossPrice())
                .consecutiveNumberFiscalReceipt(paragon.getConsecutiveNumberFiscalReceipt())
                .cashRegisterNumber(paragon.getCashRegisterNumber())
                .cashierIdentification(paragon.getCashierIdentification())
                .build();
        List<ProductDTO> products = ProductMapper.toDTOs(paragon.getProducts());
        dto.setProductDTOs(products);
        dto.setSalesTaxA(ProductMapper.sumTotalSumsForCategory(products, Category.A));
        dto.setSalesTaxB(ProductMapper.sumTotalSumsForCategory(products, Category.B));
        dto.setSalesTaxC(ProductMapper.sumTotalSumsForCategory(products, Category.C));
        dto.setSalesTaxA_PTU(
                dto.getSalesTaxA() * Category.A.getValue());
        dto.setSalesTaxB_PTU(
                dto.getSalesTaxA() * Category.B.getValue());
        dto.setSalesTaxC_PTU(
                dto.getSalesTaxA() * Category.C.getValue());
        dto.setSumPTU(dto.getSalesTaxA_PTU() + dto.getSalesTaxB_PTU() + dto.getSalesTaxC_PTU());
        dto.setTotalDiscount(ProductMapper.sumTotalDiscounts(products));
        return dto;
    }

    public static Paragon toEntity(ParagonDTO dto) {
        System.out.println(dto);
        Paragon paragon = new Paragon();
        paragon.setUuid(UUID.randomUUID().toString());
        paragon.setUserId(dto.getUserId());
        paragon.setTaxpayerName(dto.getTaxpayerName());
        paragon.setSaleAddress(dto.getSaleAddress());
        paragon.setNip(dto.getNip());
        paragon.setSequentialPrintNumber(dto.getSequentialPrintNumber());
        paragon.setSalesDate(dto.getSalesDate());
        paragon.setCurrency(Currency.getCurrencyFromString(dto.getCurrency()));
        paragon.setGrossPrice(dto.getGrossPrice());
        paragon.setConsecutiveNumberFiscalReceipt(dto.getConsecutiveNumberFiscalReceipt());
        paragon.setCashRegisterNumber(dto.getCashRegisterNumber());
        paragon.setCashierIdentification(dto.getCashierIdentification());
        List<Product> products = dto.getProductDTOs() == null ? null : dto.getProductDTOs().stream()
                .map(productDTO -> {
                    Product product = ProductMapper.toEntity(productDTO);
                    product.setParagon(paragon);
                    return product;
                }).collect(Collectors.toList());
        paragon.setProducts(products);
        System.out.println(paragon);
        return paragon;
    }

}
