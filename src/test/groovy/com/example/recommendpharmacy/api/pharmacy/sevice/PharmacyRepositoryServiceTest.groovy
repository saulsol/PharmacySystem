package com.example.recommendpharmacy.api.pharmacy.sevice

import com.example.recommendpharmacy.AbstractIntegrationContainerBaseTest
import com.example.recommendpharmacy.api.pharmacy.entity.Pharmacy
import com.example.recommendpharmacy.api.pharmacy.repository.PharmacyRepository
import org.springframework.beans.factory.annotation.Autowired


class PharmacyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepositoryService pharmacyRepositoryService

    @Autowired
    private PharmacyRepository pharmacyRepository

    def setup(){
        pharmacyRepository.deleteAll()
    }


    def "PharmacyRepository update - dirty checking success"(){

        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddress(entity.getId(),modifiedAddress)
        def result = pharmacyRepository.findAll()


        then:
        result.get(0).getPharmacyAddress() == modifiedAddress

    }


    def "PharmacyRepository update - dirty checking fail"(){

        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddressWithoutTransaction(entity.getId(),modifiedAddress)
        def result = pharmacyRepository.findAll()


        then:
        result.get(0).getPharmacyAddress() == modifiedAddress

    }

    def "self invocation" (){

        given :
        String inputAddress = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
                .pharmacyName(name)
                .pharmacyName(inputAddress)
                .longitude(longitude)
                .latitude(latitude)
                .build()

        when:
        pharmacyRepositoryService.bar(Arrays.asList(pharmacy))

        then:
        def e = thrown(RuntimeException.class)
        def result = pharmacyRepositoryService.findAll();
        result.size() == 1
        // 롤백이 되지 않았음


    }




}
