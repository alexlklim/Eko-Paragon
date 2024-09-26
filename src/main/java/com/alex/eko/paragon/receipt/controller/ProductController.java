package com.alex.eko.paragon.receipt.controller;


import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.receipt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/products")
public class ProductController {


    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void update(@RequestBody ProductDTO dto) {
        if (dto.getId() == null) {
            service.create(dto);
        } else {
            service.update(dto);
        }

    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable("id") Long id) {
        return service.getDTOById(id);
    }


    @PreAuthorize("hasRole('ROLE_MAN')")
    @GetMapping
    public List<ProductDTO> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean deleted) {
        return service.getAllByCompany(deleted);
    }

}
