package com.docencia.clase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docencia.clase.models.Purchase;


public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
   
}
