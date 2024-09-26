package com.alex.eko.paragon.company.controller;


import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.company.service.CashRegisterService;
import com.alex.eko.paragon.company.service.CompanyService;
import com.alex.eko.paragon.utils.rest.swagger.CashRegisterControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/cash-registers")
public class CashRegisterController
        implements CashRegisterControllerDocs {

    private final CashRegisterService service;

    @Autowired
    public CashRegisterController(CashRegisterService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void update(@RequestBody CashRegisterDTO dto) {
        if (dto.getId() == null) {
            service.create(dto);
        } else {
            service.update(dto);
        }

    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CashRegisterDTO getById(@PathVariable("id") Long id) {
        return service.getDTOById(id);
    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @GetMapping
    public List<CashRegisterDTO> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean deleted) {
        return service.getAllByCompany(deleted);
    }


}
