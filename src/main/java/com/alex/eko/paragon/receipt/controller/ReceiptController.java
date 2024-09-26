package com.alex.eko.paragon.receipt.controller;


import com.alex.eko.paragon.receipt.dto.ReceiptDTO;
import com.alex.eko.paragon.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/receipts")
public class ReceiptController {


    private final ReceiptService service;

    @Autowired
    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void update(@RequestBody ReceiptDTO dto) {
        if (dto.getId() == null) {
            service.create(dto);
        }

    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ReceiptDTO getById(@PathVariable("id") Long id) {
        return service.getDTOById(id);
    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @GetMapping
    public List<ReceiptDTO> getAllByCompany(
            @RequestParam(required = false, defaultValue = "false") Boolean deleted) {
        return service.getAllByCompany(deleted);
    }

    @GetMapping("/my")
    public List<ReceiptDTO> getAllByUser(
            @RequestParam(required = false, defaultValue = "false") Boolean deleted) {
        return service.getAllByUserId(deleted);
    }

}
