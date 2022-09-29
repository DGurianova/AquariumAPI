package com.gurianova.aquariumapi.service;

import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.RequestOwnerDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.dto.ResponseOwnerDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.persistance.entity.Owner;
import com.gurianova.aquariumapi.persistance.repository.FishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class FishServiceImplementationTest {
    @MockBean
    FishRepository mockFishRepository = Mockito.mock(FishRepository.class);

    private FishServiceImplementation fishService;

    @MockBean
    OwnerService mockOwnerService = Mockito.mock(OwnerService.class);


    @BeforeEach
    private void beforeSetup() {
        fishService = new FishServiceImplementation();
        fishService.setOwnerService(mockOwnerService);
    }

    @Test
    void createOrEditFishWhenTheIdIsNotFound() {
        RequestOwnerDTO requestOwnerDTO = new RequestOwnerDTO(
                1,
                "Peter",
                "Jackson",
                DateFormat.parseTimestamp("2010-05-15"),
                "Texas 123N"
        );
        ResponseOwnerDTO responseOwnerDTO = new ResponseOwnerDTO(
                1,
                "Peter",
                "Jackson",
                DateFormat.parseTimestamp("2010-05-15"),
                "Texas 123N"
        );

        RequestFishDTO requestFishDTO = new RequestFishDTO(
                null,
                "Goldfish",
                2,
                "worms",
                DateFormat.parseTimestamp("2022-07-25"),
                requestOwnerDTO

        );
        ResponseFishDTO responseFishDTO = new ResponseFishDTO(
                null,
                "Goldfish",
                2,
                "worms",
                DateFormat.parseTimestamp("2022-07-25"),
                responseOwnerDTO
        );

        Owner owner = Owner.builder().build();//Can be empty cause convertOwnerToDTO() is mocked below


        fishService.setFishRepository(mockFishRepository);
        when(mockFishRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(mockFishRepository.save(any(Fish.class))).then(i -> i.getArgument(0));
        when(mockOwnerService.convertDTOToOwner(any(RequestOwnerDTO.class))).thenReturn(owner);
        when(mockOwnerService.convertOwnerToDTO(any(Owner.class))).thenReturn(responseOwnerDTO);

        ResponseFishDTO fishCreatedByService = fishService.createOrEditFish(requestFishDTO);

        assertEquals(fishCreatedByService.getName(), responseFishDTO.getName());
        assertEquals(fishCreatedByService.getAgeYears(), responseFishDTO.getAgeYears());
        assertEquals(fishCreatedByService.getPreferredFood(), responseFishDTO.getPreferredFood());
        assertEquals(fishCreatedByService.getId(), responseFishDTO.getId());
        assertEquals(fishCreatedByService.getDateOfPurchase(), responseFishDTO.getDateOfPurchase());
        assertEquals(fishCreatedByService.getResponseOwnerDTO(), responseFishDTO.getResponseOwnerDTO());

    }

    @Test
    void createOrEditFishWhenTheIdIsFound() {

        RequestOwnerDTO requestOwnerDTO = new RequestOwnerDTO(
                1,
                "Peter",
                "Jackson",
                DateFormat.parseTimestamp("2010-05-15"),
                "Texas 123N"
        );
        ResponseOwnerDTO responseOwnerDTO = new ResponseOwnerDTO(
                1,
                "Peter",
                "Jackson",
                DateFormat.parseTimestamp("2010-05-15"),
                "Texas 123N"
        );

        RequestFishDTO requestFishDTO = new RequestFishDTO(
                33,
                "Goldfish",
                2,
                "worms",
                DateFormat.parseTimestamp("2022-07-25"),
                requestOwnerDTO

        );
        ResponseFishDTO responseFishDTO = new ResponseFishDTO(
                33,
                "Goldfish",
                2,
                "worms",
                DateFormat.parseTimestamp("2022-07-25"),
                responseOwnerDTO
        );

        Owner owner = Owner.builder().build();
        Fish fish = Fish.builder().build();

        fishService.setFishRepository(mockFishRepository);
        when(mockFishRepository.findById(anyInt())).thenReturn(Optional.of(fish));
        when(mockFishRepository.save(any(Fish.class))).then(i -> i.getArgument(0));
        when(mockOwnerService.convertDTOToOwner(any(RequestOwnerDTO.class))).thenReturn(owner);
        when(mockOwnerService.convertOwnerToDTO(any(Owner.class))).thenReturn(responseOwnerDTO);

        ResponseFishDTO fishCreatedByService = fishService.createOrEditFish(requestFishDTO);

        assertEquals(fishCreatedByService.getName(), responseFishDTO.getName());
        assertEquals(fishCreatedByService.getAgeYears(), responseFishDTO.getAgeYears());
        assertEquals(fishCreatedByService.getPreferredFood(), responseFishDTO.getPreferredFood());
        assertEquals(fishCreatedByService.getId(), responseFishDTO.getId());
        assertEquals(fishCreatedByService.getDateOfPurchase(), responseFishDTO.getDateOfPurchase());
    }
}