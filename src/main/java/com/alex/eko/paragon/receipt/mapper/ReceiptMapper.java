package com.alex.eko.paragon.receipt.mapper;

import com.alex.eko.paragon.company.service.CashRegisterService;
import com.alex.eko.paragon.company.service.CompanyService;
import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.domain.enums.Category;
import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.utils.abstraction.AbstractMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReceiptMapper
        extends AbstractMapper<Receipt, ReceiptDTO> {


    private final ProductMapper productMapper;

    @Override
    protected void mapToDTO(ReceiptDTO dto, Receipt entity) {
        dto.setCreated(entity.getCreated());
        dto.setUuid(entity.getUuid());
        dto.setReceiverId(entity.getReceiverId());

        dto.setTaxpayerName(entity.getCashRegister().getCompany().getTaxpayerName());
        dto.setSaleAddress(entity.getCashRegister().getCompany().getSaleAddress());

        dto.setNip(entity.getCashRegister().getCompany().getNip());
        dto.setSequentialPrintNumber(entity.getSequentialPrintNumber());
        dto.setSalesDate(entity.getSalesDate());

        dto.setCurrency(entity.getCurrency());

        dto.setConsecutiveNumberFiscalReceipt(entity.getConsecutiveNumberFiscalReceipt());

        dto.setCashRegisterNumber(entity.getCashRegister().getCashRegisterNumber());
        dto.setCashierIdentification(entity.getEmployee().getCashierIdentification());

        dto.setProductDTOs(productMapper.toDTOs(entity.getProducts()));


        dto.setSalesTaxA(Category.A.getValue());
        dto.setSalesTaxB(Category.B.getValue());
        dto.setSalesTaxC(Category.C.getValue());
        dto.setSalesTaxA_PTU(countPTU(Category.A, entity.getProducts()));
        dto.setSalesTaxB_PTU(countPTU(Category.B, entity.getProducts()));
        dto.setSalesTaxC_PTU(countPTU(Category.C, entity.getProducts()));

        dto.setSumPTU(dto.getSalesTaxA_PTU() + dto.getSalesTaxB_PTU() + dto.getSalesTaxC_PTU());


        dto.setTotalDiscount(calculateTotalDiscount(entity.getProducts()));
        dto.setGrossPrice(calculateGrossPrice(entity.getProducts(), dto.getTotalDiscount()));

        dto.setConsecutiveNumberFiscalReceipt(entity.getConsecutiveNumberFiscalReceipt());

        dto.setCashRegisterNumber(entity.getCashRegister().getCashRegisterNumber());
        dto.setCashierIdentification(entity.getEmployee().getCashierIdentification());

        dto.setProductDTOs(productMapper.toDTOs(entity.getProducts()));
    }

    private Double countPTU(Category category, List<Product> products) {
        // Initialize variables for total tax calculations
        double totalPTU = 0.0;

        // Loop through each product to calculate PTU based on its category
        for (Product product : products) {
            // Check if the product belongs to the specified category
            if (product.getCategory() == category) {
                // Calculate the sales tax for the product
                double salesTax = product.getUnitPrice() * product.getAmount() * category.getValue();
                // Add the sales tax to the total PTU
                totalPTU += salesTax;
            }
        }

        return totalPTU; // Return the total PTU for the specified category
    }



    private Double calculateGrossPrice(List<Product> products, Double totalDiscount) {
        double grossPrice = 0.0;

        for (Product product : products) {
            // Calculate the total price for each product and accumulate
            grossPrice += product.getUnitPrice() * product.getAmount();
        }

        // Subtract the total discount from the gross price
        grossPrice -= totalDiscount;

        return grossPrice; // Return the gross price after applying the total discount
    }



    private Double calculateTotalDiscount(List<Product> products) {
        double totalDiscount = 0.0;

        for (Product product : products) {
            if (product.getDiscount() != null) {
                // Calculate the discount for each product and accumulate
                totalDiscount += product.getDiscount() * product.getAmount();
            }
        }

        return totalDiscount; // Return the total discount
    }

}
