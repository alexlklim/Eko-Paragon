package com.alex.eko.paragon.company.controller;


import com.alex.eko.paragon.company.dto.CompanyDTO;
import com.alex.eko.paragon.company.service.CompanyService;
import com.alex.eko.paragon.utils.rest.swagger.CompanyControllerDocs;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/company")
public class CompanyController
        implements CompanyControllerDocs {

    private final CompanyService service;


    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void update(@RequestBody CompanyDTO dto) {
        service.update(dto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CompanyDTO getById(@PathVariable("id") Long id) {
        return service.getDTOById(id);
    }


    @Override
    @Secured("ROLE_MAN")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/my")
    public CompanyDTO getMyCompany() {
        return service.getMyCompany();
    }

}
