package com.example.recommendpharmacy.api.service;

import com.example.recommendpharmacy.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoAddressSearchService {

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    @Value("${kakao.rest.api.key}") // .yml에 설정되어 있는 변수로 선언
    private String kakaoRestApiKey; // 키 값을 헤더에 담아야 한다.


    public KakaoApiResponseDto requestAddressSearch(String address){

        if(ObjectUtils.isEmpty(address)) return null; // Todo : Exception 처리 해야함


        URI uri = kakaoUriBuilderService.buildUriByAddressSearch(address);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // kakao api 호출
       return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();

    }






}
