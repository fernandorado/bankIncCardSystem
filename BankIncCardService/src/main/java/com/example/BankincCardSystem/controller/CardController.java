package com.example.BankincCardSystem.controller;

import com.example.BankincCardSystem.model.Card;
import com.example.BankincCardSystem.service.CardService;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{productId}/number")
    public String generateCardNumber(@PathVariable String productId) {
        return cardService.generateCardNumber(productId);
    }

    @PostMapping("/enroll")
    public Card activateCard(@RequestBody Map<String, String> request) {
        return cardService.activateCard(request.get("cardId"));
    }

    @DeleteMapping("/{cardId}")
    public Card blockCard(@PathVariable String cardId) {
        return cardService.blockCard(cardId);
    }

    @PostMapping("/balance")
    public Card rechargeCard(@RequestBody Map<String, String> request) {
        BigDecimal balance = new BigDecimal(request.get("balance"));
        return cardService.rechargeCard(request.get("cardId"), balance);
    }

    @GetMapping("/balance/{cardId}")
    public BigDecimal getBalance(@PathVariable String cardId) {
        return cardService.getBalance(cardId);
    }
}
