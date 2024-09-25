package com.alex.eko.paragon.receipt.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}/receipts")
@RequiredArgsConstructor
public class ReceiptController {
}
