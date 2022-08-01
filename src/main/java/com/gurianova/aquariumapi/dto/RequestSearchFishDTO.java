package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSearchFishDTO {
    private Integer id;
    private String name;
    private Integer ageYears;
    private String preferredFood;
}
