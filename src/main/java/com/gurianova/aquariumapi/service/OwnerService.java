package com.gurianova.aquariumapi.service;


import com.gurianova.aquariumapi.dto.RequestOwnerDTO;
import com.gurianova.aquariumapi.dto.ResponseOwnerDTO;
import com.gurianova.aquariumapi.persistance.entity.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    ResponseOwnerDTO convertOwnerToDTO(Owner owner);

    Owner convertDTOToOwner(RequestOwnerDTO requestOwnerDTO);

}
