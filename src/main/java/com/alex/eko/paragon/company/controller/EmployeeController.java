package com.alex.eko.paragon.company.controller;


import com.alex.eko.paragon.company.dto.EmployeeDTO;
import com.alex.eko.paragon.company.service.EmployeeService;
import com.alex.eko.paragon.utils.rest.swagger.EmployeeControllerDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/employees")
public class EmployeeController
        implements EmployeeControllerDocs {


    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void update(@RequestBody EmployeeDTO dto) {
        if (dto.getId() == null) {
            service.create(dto);
        } else {
            service.update(dto);
        }

    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable("id") Long id) {
        return service.getDTOById(id);
    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @GetMapping
    public List<EmployeeDTO> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean deleted) {
        return service.getAllByCompany(deleted);
    }
}
