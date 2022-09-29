package com.gurianova.aquariumapi.service;


import com.gurianova.aquariumapi.dto.RequestOwnerDTO;
import com.gurianova.aquariumapi.dto.ResponseOwnerDTO;
import com.gurianova.aquariumapi.persistance.entity.Owner;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImplementation implements OwnerService {
    @Override
    public ResponseOwnerDTO convertOwnerToDTO(Owner owner) {
        return ResponseOwnerDTO.builder()
                .id(owner.getOwnerId())
                .name(owner.getName())
                .secondName(owner.getSecondName())
                .dateOfBirth(owner.getDateOfBirth())
                .address(owner.getAddress())
                .build();
    }

    @Override
    public Owner convertDTOToOwner(RequestOwnerDTO requestOwnerDTO) {
        return Owner.builder()
                .ownerId(requestOwnerDTO.getId())
                .name(requestOwnerDTO.getName())
                .secondName(requestOwnerDTO.getSecondName())
                .dateOfBirth(requestOwnerDTO.getDateOfBirth())
                .address(requestOwnerDTO.getAddress())
                .build();
    }
}
