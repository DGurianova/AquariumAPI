package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSearchFishErrorDTO extends ResponseSearchFishDTO {
    private Integer code;
    private String description;
}
