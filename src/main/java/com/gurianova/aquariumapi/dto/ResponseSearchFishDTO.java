package com.gurianova.aquariumapi.dto;

import com.gurianova.aquariumapi.persistance.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSearchFishDTO {
    private Integer id;
    private String name;
    private Integer ageYears;
    private String preferredFood;
    private Timestamp dateOfPurchase;
    private Owner owner;
}
