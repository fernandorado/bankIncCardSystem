package com.example.BankincCardSystem.service;

import com.example.BankincCardSystem.model.Card;
import com.example.BankincCardSystem.model.Transaction;
import com.example.BankincCardSystem.repository.CardRepository;
import com.example.BankincCardSystem.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    public Transaction purchase(String cardId, BigDecimal amount) {

        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la tarjeta no puede estar vacío");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la compra debe ser mayor que cero");
        }
        try {
            Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));

            if (!card.isActive()) {
                throw new IllegalStateException("La tarjeta no está activa");
            }
            if (card.isBlocked()) {
                throw new IllegalStateException("La tarjeta está bloqueada");
            }
            if (!"USD".equals(card.getCurrency())) { // Verifica que la moneda sea USD
                throw new IllegalArgumentException("Solo se permiten transacciones en dólares.");
            }
            if (card.getBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Saldo insuficiente para realizar la compra");
            }
            if (card.getExpiryDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("La tarjeta está vencida");
            }

            card.setBalance(card.getBalance().subtract(amount));
            cardRepository.save(card);

            Transaction transaction = new Transaction();
            transaction.setCardId(cardId);
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setCancelled(false);
            return transactionRepository.save(transaction);
        } catch (NoSuchElementException ex) {
            throw new RuntimeException("La tarjeta no se encontró: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            throw new RuntimeException("Error al realizar la compra: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Error al realizar la compra: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado al realizar la compra: " + ex.getMessage());
        }

    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new NoSuchElementException("Transaction not found"));
    }

    public Transaction cancelTransaction(String cardId, Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new NoSuchElementException("Transaction not found"));

        if (transaction.isCancelled() || Duration.between(transaction.getTransactionDate(), LocalDateTime.now()).toHours() > 24) {
            throw new IllegalStateException("La transacción no se puede cancelar");
        }

        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la tarjeta no puede estar vacío");
        }
        
        if (transactionId == null) {
            throw new IllegalArgumentException("El ID de la tarjeta no puede estar vacío");
        }

        transaction.setCancelled(true);
        transactionRepository.save(transaction);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> new NoSuchElementException("Tarjeta no encontrada"));
        card.setBalance(card.getBalance().add(transaction.getAmount()));
        cardRepository.save(card);

        return transaction;
    }
}
