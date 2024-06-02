/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BankincCardSystem.controller;

import com.example.BankincCardSystem.model.Transaction;
import com.example.BankincCardSystem.service.TransactionService;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fernando Dorado
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/purchase")
    public Transaction purchase(@RequestBody Map<String, Object> request) {
        String cardId = (String) request.get("cardId");
        BigDecimal price = new BigDecimal(request.get("price").toString()); // Convertir a BigDecimal

        return transactionService.purchase(cardId, price);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransaction(@PathVariable Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @PostMapping("/anulation")
    public Transaction cancelTransaction(@RequestBody Map<String, Object> request) {
        String cardId = (String) request.get("cardId");
        Long transactionId = ((Number) request.get("transactionId")).longValue();
        return transactionService.cancelTransaction(cardId, transactionId);
    }
}
