package com.example.BankincCardSystem.service;

import com.example.BankincCardSystem.model.Card;
import com.example.BankincCardSystem.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    // Test para el método generateCardNumber
    @Test
    public void testGenerateCardNumber() {
        System.out.println("generateCardNumber");
        String productId = "123456";
        String result = cardService.generateCardNumber(productId);
        assertNotNull(result); // Verificar que el número de tarjeta generado no sea nulo
        assertEquals(16, result.length()); // Verificar que el número de tarjeta generado tenga longitud 16
    }

    // Test para el método activateCard
    @Test
    public void testActivateCard() {
        System.out.println("activateCard");
        String cardId = "1234569616827042";
        Card card = new Card();
        card.setId(cardId);
        card.setActive(false);

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card activatedCard = cardService.activateCard(cardId);
        assertNotNull(activatedCard); // Verificar que la tarjeta activada no sea nula
        assertTrue(activatedCard.isActive()); // Verificar que la tarjeta está activada

        verify(cardRepository, times(1)).findById(cardId);
        verify(cardRepository, times(1)).save(card);
    }

    // Test para el método blockCard
    @Test
    public void testBlockCard() {
        System.out.println("blockCard");
        String cardId = "1234569616827042";
        Card card = new Card();
        card.setId(cardId);
        card.setBlocked(false);

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card blockedCard = cardService.blockCard(cardId);
        assertNotNull(blockedCard); // Verificar que la tarjeta bloqueada no sea nula
        assertTrue(blockedCard.isBlocked()); // Verificar que la tarjeta está bloqueada

        verify(cardRepository, times(1)).findById(cardId);
        verify(cardRepository, times(1)).save(card);
    }

    // Test para el método rechargeCard
    @Test
    public void testRechargeCard() {
        System.out.println("rechargeCard");
        String cardId = "1234562621180146";
        BigDecimal amount = new BigDecimal("10000");
        Card card = new Card();
        card.setId(cardId);
        card.setBalance(BigDecimal.ZERO);
        card.setActive(true); // Asegúrate de que la tarjeta esté activa

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card rechargedCard = cardService.rechargeCard(cardId, amount);
        assertNotNull(rechargedCard); // Verificar que la tarjeta recargada no sea nula
        assertEquals(amount, rechargedCard.getBalance()); // Verificar que el saldo de la tarjeta sea igual al monto recargado

        verify(cardRepository, times(1)).findById(cardId);
        verify(cardRepository, times(1)).save(card);
    }

    // Test para el método getBalance
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        String cardId = "1234569616827042";
        BigDecimal expectedBalance = new BigDecimal("10000"); // Crear un BigDecimal para el saldo esperado
        Card card = new Card();
        card.setId(cardId);
        card.setBalance(expectedBalance);

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));

        BigDecimal result = cardService.getBalance(cardId);
        assertNotNull(result); // Verificar que el saldo obtenido no sea nulo
        assertEquals(expectedBalance, result); // Verificar que el saldo obtenido sea igual al saldo esperado

        verify(cardRepository, times(1)).findById(cardId);
    }
}
