package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseFishDTO {
    private String name;
    private Integer ageYears;
    private String preferredFood;
}