/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BankincCardSystem.service;

import com.example.BankincCardSystem.model.Card;
import com.example.BankincCardSystem.repository.CardRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fernando Dorado
 */
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public String generateCardNumber(String productId) {

        try {
            // Verificar si ya existe una tarjeta con el mismo identificador
            if (cardRepository.existsById(productId)) {
                throw new IllegalStateException("Ya existe una tarjeta con el identificador proporcionado");
            }

            String randomDigits = generateRandomDigits(10);
            String cardNumber = productId + randomDigits;

            // Calcular la fecha de vencimiento (3 años a partir de la fecha actual)
            LocalDate expiryDate = LocalDate.now().plusYears(3);

            // Guardar el número de tarjeta en la base de datos
            Card card = new Card();
            card.setId(cardNumber);
            card.setProductId(productId);
            card.setActive(false); // Por defecto, la tarjeta se guarda como inactiva
            card.setExpiryDate(expiryDate); // Establecer la fecha de vencimiento
            card.setCurrency("USD");
            cardRepository.save(card);

            return cardNumber;
        } catch (Exception ex) {
            // Manejo de la excepción: por ejemplo, registrándola, lanzando una excepción personalizada o devolviendo un mensaje de error
            throw new RuntimeException("Error al generar el número de tarjeta: " + ex.getMessage());
        }

    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomDigits.append(random.nextInt(10));
        }
        return randomDigits.toString();
    }

    public Card activateCard(String cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));
        card.setActive(true);
        return cardRepository.save(card);
    }

    public Card blockCard(String cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));
        card.setBlocked(true);
        return cardRepository.save(card);
    }

    public Card rechargeCard(String cardId, BigDecimal amount) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));

        if (!card.isActive()) {
            throw new IllegalStateException("La tarjeta no está activa");
        }

        card.setBalance(card.getBalance().add(amount));
        return cardRepository.save(card);
    }

    public BigDecimal getBalance(String cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));
        return card.getBalance();
    }
}
