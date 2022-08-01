package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
public class ResponseFishDTO {
    private Integer id;
    private String name;
    private Integer ageYears;
    private String preferredFood;
    private LocalDate dateOfPurchase;
}
