package com.gurianova.aquariumapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@Builder
public class RequestFishDTO {
    private Integer id;
    private String name;
    private Integer ageYears;
    private String preferredFood;
    private Timestamp dateOfPurchase;
    private RequestOwnerDTO requestOwnerDTO;


}
