package com.example.recommendpharmacy.api.pharmacy.repository

import com.example.recommendpharmacy.AbstractIntegrationContainerBaseTest
import com.example.recommendpharmacy.api.pharmacy.entity.Pharmacy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime


class PharmacyRepositoryTest extends AbstractIntegrationContainerBaseTest {

    // CRUD 테스트
    @Autowired
    private PharmacyRepository pharmacyRepository;

    // @BeforeEach 같은 느낌
    // 싱글톤 테스트 컨테이너라서 미리 클린해야 한다.
    def setup(){
        pharmacyRepository.deleteAll()
    }


    def "PharmacyRepository save"(){

        given:
        String address = "서울 특별시 성북구 중앙동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
            .pharmacyAddress(address)
            . pharmacyName(name)
            . latitude(latitude)
            .longitude(longitude)
            .build()


        when:
        def result = pharmacyRepository.save(pharmacy)

        then:
        result.getPharmacyAddress() == address
        result.getPharmacyName() == name
        result.getLatitude() == latitude
        result.getLongitude() == longitude

    }

    def "PharmacyRepository saveAll"(){
        given:
        String address = "서울 특별시 성북구 중앙동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                . pharmacyName(name)
                . latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        pharmacyRepository.saveAll(Arrays.asList(pharmacy))
        def result = pharmacyRepository.findAll()

        then:
        result.size() == 1

    }

    def "BaseTimeEntity 등록"(){

        given:
        LocalDateTime now = LocalDateTime.now();

        String address = "서울 특별시 성북구 중앙동"
        String name = "은혜 약국"


        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                . pharmacyName(name)
                .build()

        when:
        pharmacyRepository.save(pharmacy)
        def result = pharmacyRepository.findAll()

        then:
        result.get(0).getCreatedDate().isAfter(now)
        result.get(0).getModifiedDate().isAfter(now)

    }







}
