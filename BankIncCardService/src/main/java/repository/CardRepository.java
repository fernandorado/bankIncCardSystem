/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import com.example.BankincCardSystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fernando Dorado
 */
public interface CardRepository extends JpaRepository<Card, String>{
    
}
