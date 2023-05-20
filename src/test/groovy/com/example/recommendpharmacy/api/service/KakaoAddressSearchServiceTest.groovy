package com.example.recommendpharmacy.api.service

import com.example.recommendpharmacy.AbstractIntegrationContainerBaseTest
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Slf4j
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
        given:
        def address = "서울 성북구 종암로 10길"

        when:
        def result = addressSearchService.requestAddressSearch(address)

        then:
        result.getDocumentList().size() > 0
        result.metaDto.totalCount > 0
        result.documentList.get(0).addressName != null


    }



}
