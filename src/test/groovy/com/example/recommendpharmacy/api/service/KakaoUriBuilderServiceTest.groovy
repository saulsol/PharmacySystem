package com.example.recommendpharmacy.api.service

import spock.lang.Specification

import java.nio.charset.StandardCharsets

class KakaoUriBuilderServiceTest extends Specification {

    private KakaoUriBuilderService builderService;

    // @beforeEach 와 비슷
    def setup(){
        builderService = new KakaoUriBuilderService();
    }

    def "buildUriByAddressSearch - 한글 파라미터의 경우 정상적으로 인코딩"(){
        given :
        String address = "서울 성북구"
        def charset = StandardCharsets.UTF_8

        when :
        def uri = builderService.buildUriByAddressSearch(address)
        def decodeResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodeResult == "https://dapi.kakao.com/v2/local/search/address.json?query=서울 성북구"

    }

}
