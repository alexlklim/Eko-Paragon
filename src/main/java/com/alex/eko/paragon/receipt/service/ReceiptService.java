package com.alex.eko.paragon.receipt.service;

import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.company.service.CashRegisterService;
import com.alex.eko.paragon.company.service.EmployeeService;
import com.alex.eko.paragon.receipt.domain.Product;
import com.alex.eko.paragon.receipt.domain.Receipt;
import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.receipt.mapper.ReceiptMapper;
import com.alex.eko.paragon.receipt.repo.ProductRepo;
import com.alex.eko.paragon.receipt.repo.ReceiptRepo;
import com.alex.eko.paragon.security.repo.SH;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class ReceiptService
        extends AbstractBaseService<Receipt, ReceiptDTO, ReceiptRepo, ReceiptMapper> {

    private final CompanyRepo companyRepo;
    private final CashRegisterService cashRegisterService;
    private final EmployeeService employeeService;

    private final ProductRepo productRepo;

    @Autowired
    public ReceiptService(ReceiptRepo repository, ReceiptMapper mapper, CompanyRepo companyRepo, CashRegisterService cashRegisterService, EmployeeService employeeService, ProductRepo productRepo) {
        super(repository, mapper, Receipt.class);
        this.companyRepo = companyRepo;
        this.cashRegisterService = cashRegisterService;
        this.employeeService = employeeService;
        this.productRepo = productRepo;
    }


    @Override
    protected void updateEntityFromDTO(Receipt entity, ReceiptDTO dto) {
        entity.setUuid(UUID.randomUUID().toString());
        entity.setReceiverId(dto.getReceiverId());
        entity.setSequentialPrintNumber(dto.getSequentialPrintNumber());
        entity.setConsecutiveNumberFiscalReceipt(dto.getConsecutiveNumberFiscalReceipt());
        entity.setSalesDate(dto.getSalesDate());

        entity.setCurrency(dto.getCurrency());

        entity.setCashRegister(cashRegisterService.getActiveById(dto.getCashRegisterId()));
        entity.setEmployee(employeeService.getActiveById(dto.getEmployeeId()));

        if (entity.getProducts() == null) {
            entity.setProducts(new ArrayList<>()); // or use ArrayList depending on your needs
        }

        // Add products from the repository to the entity
        entity.getProducts().addAll(productRepo.findByIds(dto.getProductIds(), false));

    }

    public List<ReceiptDTO> getAllByCompany(Boolean deleted) {
        Company company = companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), deleted)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapper.toDTOs(repository.findByCompany(company.getId(), deleted));
    }

    public List<ReceiptDTO> getAllByUserId(Boolean deleted) {
        return mapper.toDTOs(repository.findByReceiver(SH.getUserId(), deleted));
    }
}
