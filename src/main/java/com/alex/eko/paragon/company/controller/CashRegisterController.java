package com.alex.eko.paragon.company.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/cash-registers")
@RequiredArgsConstructor
public class CashRegisterController {
}
