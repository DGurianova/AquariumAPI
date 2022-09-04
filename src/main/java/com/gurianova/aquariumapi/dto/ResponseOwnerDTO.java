package com.gurianova.aquariumapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
public class ResponseOwnerDTO {

    private String name;
    private String secondName;
    private Timestamp dateOfBirth;
    private String address;
}
