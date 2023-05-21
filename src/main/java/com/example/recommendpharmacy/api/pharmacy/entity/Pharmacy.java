package com.example.recommendpharmacy.api.pharmacy.entity;

import com.example.recommendpharmacy.api.pharmacy.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pharmacy")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pharmacyName;

    private String pharmacyAddress;

    private double latitude; //위도, Y

    private double longitude; // 경도, X

    public void changePharmacyAddress(String address){
        this.pharmacyAddress = address;
    }


}
