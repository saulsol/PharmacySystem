package com.example.recommendpharmacy.api.pharmacy.sevice;

import com.example.recommendpharmacy.api.pharmacy.entity.Pharmacy;
import com.example.recommendpharmacy.api.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    public void bar(List<Pharmacy> pharmacyList){
        log.info("bar CurrentTransactionName : " + TransactionSynchronizationManager.getCurrentTransactionName());
        foo(pharmacyList);
    }

    @Transactional
    public void foo(List<Pharmacy> pharmacyList){
        log.info("bar CurrentTransactionName : " + TransactionSynchronizationManager.getCurrentTransactionName());

        pharmacyList.forEach(
                pharmacy -> {
                    pharmacyRepository.save(pharmacy);
                    throw new RuntimeException("error");
                    // @Transactional 은 기본적으로 RuntimeException 이 발생하면 롤백을 실행한다.
                }
        );

    }





    @Transactional
    public void updateAddress(Long id, String address){

        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)){
            log.error("[PharmacyRepositoryService updateAddress] not found id: {} ", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }

    // for test
    public void updateAddressWithoutTransaction(Long id, String address){

        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)){
            log.error("[PharmacyRepositoryService updateAddress] not found id: {} ", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }





}
