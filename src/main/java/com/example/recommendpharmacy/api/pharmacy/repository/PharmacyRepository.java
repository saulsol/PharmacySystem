package com.example.recommendpharmacy.api.pharmacy.repository;

import com.example.recommendpharmacy.api.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {


}
