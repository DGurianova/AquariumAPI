package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestOwnerDTO {
    private Integer id;
    private String name;
    private String secondName;
    private Timestamp dateOfBirth;
    private String address;
}
