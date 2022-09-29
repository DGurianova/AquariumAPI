package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class ResponseOwnerDTO {
    private Integer id;
    private String name;
    private String secondName;
    private Timestamp dateOfBirth;
    private String address;
}
