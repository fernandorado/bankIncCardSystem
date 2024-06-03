package com.example.BankincCardSystem.service;

import com.example.BankincCardSystem.model.Card;
import com.example.BankincCardSystem.model.Transaction;
import com.example.BankincCardSystem.repository.CardRepository;
import com.example.BankincCardSystem.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testPurchase() {
        // Configurar los datos de prueba
        String cardId = "123456";
        BigDecimal amount = new BigDecimal("100");

        Card card = new Card();
        card.setId(cardId);
        card.setBalance(new BigDecimal("200"));
        card.setActive(true);
        card.setBlocked(false);
        card.setCurrency("USD");
        card.setExpiryDate(LocalDate.now().plusDays(1));

        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar el método de prueba
        Transaction result = transactionService.purchase(cardId, amount);

        // Verificar los resultados esperados
        assertNotNull(result);
        assertEquals(cardId, result.getCardId());
        assertEquals(amount, result.getAmount());
        assertFalse(result.isCancelled());

        // Verificar que se han llamado a los métodos de repositorio
        verify(cardRepository, times(1)).save(card);
        verify(transactionRepository, times(1)).save(result);
    }

    @Test
    public void testGetTransaction() {
        System.out.println("getTransaction");
        Long transactionId = 1L;

        // Configurar una transacción de prueba
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(new BigDecimal("100"));
        transaction.setCardId("123456");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCancelled(false);

        // Configurar el comportamiento del mock
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        // Ejecutar el método bajo prueba
        Transaction result = transactionService.getTransaction(transactionId);

        // Verificar los resultados
        assertNotNull(result);
        assertEquals(transactionId, result.getId());
        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(transaction.getCardId(), result.getCardId());
        assertEquals(transaction.getTransactionDate(), result.getTransactionDate());
        assertEquals(transaction.isCancelled(), result.isCancelled());

        // Verificar que se llamó al método del repositorio
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    public void testCancelTransaction() {
        System.out.println("cancelTransaction");
        String cardId = "123456";
        Long transactionId = 1L;

        // Configurar la tarjeta
        Card card = new Card();
        card.setId(cardId);
        card.setBalance(new BigDecimal("100"));
        card.setActive(true);
        card.setBlocked(false);

        // Configurar la transacción
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setCardId(cardId);
        transaction.setAmount(new BigDecimal("50"));
        transaction.setTransactionDate(LocalDateTime.now().minusHours(1));
        transaction.setCancelled(false);

        // Mock repository behaviors
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar el método bajo prueba
        Transaction result = transactionService.cancelTransaction(cardId, transactionId);

        // Verificar los resultados
        assertNotNull(result);
        assertEquals(transactionId, result.getId());
        assertTrue(result.isCancelled());

        // Verificar que el saldo de la tarjeta se actualiza
        assertEquals(new BigDecimal("150"), card.getBalance());

        // Verificar que se llamaron a los métodos de guardar
        verify(transactionRepository, times(1)).save(result);
        verify(cardRepository, times(1)).save(card);
    }
}
