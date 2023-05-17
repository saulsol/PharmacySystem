package com.example.recommendpharmacy.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto {

    // 필요한 데이터만 받아오겠다.

    @JsonProperty("total_count") // JSON으로 응답을 받는 경우 JSON 필드와 해당 필드를 맵핑시켜준다.
    private Integer totalCount;





}
