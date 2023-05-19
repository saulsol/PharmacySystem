package com.example.recommendpharmacy.api.service

import com.example.recommendpharmacy.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private KakaoAddressSearchService addressSearchService;

    def "address 파라미터 값이 null이면, requestAddressSearch 메소드는 null을 리턴한다."(){

        given:
        def address = null

        when:
        def result = addressSearchService.requestAddressSearch(address)

        then:
        result == null
    }


    def "주소값이 valid하다면, requestAddressSearch 메소드는 정상적으로 documnet를 반환한다"(){

    }



}
