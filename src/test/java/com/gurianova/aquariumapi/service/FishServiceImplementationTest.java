package com.gurianova.aquariumapi.service;

import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.persistance.repository.FishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyInt;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class FishServiceImplementationTest {
    @MockBean
    FishRepository mockFishRepository = Mockito.mock(FishRepository.class);

    private FishServiceImplementation service;

    @BeforeEach
    private void beforeSetup() {
        service = new FishServiceImplementation();
    }

    @Test
    void createOrEditFishWhenTheIdIsNotFound() {
        RequestFishDTO dto = new RequestFishDTO(
                null,
                "Goldfish",
                2,
                "worms",
                "2022-07-25"
        );

        Fish fishManuallyCreated = Fish.builder()
                .name("Goldfish")
                .ageYears(2)
                .preferredFood("worms")
                .dateOfPurchase(DateFormat.parseTimestamp("2022-07-25"))
                .build();

        service.setFishRepository(mockFishRepository);
        when(mockFishRepository.findById(anyInt())).thenReturn(Optional.of(fishManuallyCreated));
        when(mockFishRepository.save(Mockito.any(Fish.class))).then(i -> i.getArgument(0));

        ResponseFishDTO fishCreatedByService = service.createOrEditFish(dto);

        assertEquals(fishCreatedByService.getName(), fishManuallyCreated.getName());
        assertEquals(fishCreatedByService.getAgeYears(), fishManuallyCreated.getAgeYears());
        assertEquals(fishCreatedByService.getPreferredFood(), fishManuallyCreated.getPreferredFood());
        assertEquals(fishCreatedByService.getId(), fishManuallyCreated.getId());
        assertEquals(fishCreatedByService.getDateOfPurchase(), fishManuallyCreated.getDateOfPurchase());
    }

    @Test
    void createOrEditFishWhenTheIdIsFound() {
        RequestFishDTO dto = new RequestFishDTO(
                1,
                "Goldfish",
                2,
                "worms",
                "2022-07-25"
        );

        Fish fishManuallyCreated = Fish.builder()
                .id(1)
                .name("Goldfish")
                .ageYears(2)
                .preferredFood("worms")
                .dateOfPurchase(DateFormat.parseTimestamp("2022-07-25"))
                .build();

        service.setFishRepository(mockFishRepository);
        when(mockFishRepository.findById(anyInt())).thenReturn(Optional.of(fishManuallyCreated));
        when(mockFishRepository.save(Mockito.any(Fish.class))).then(i -> i.getArgument(0));

        ResponseFishDTO fishCreatedByService = service.createOrEditFish(dto);

        assertEquals(fishCreatedByService.getName(), fishManuallyCreated.getName());
        assertEquals(fishCreatedByService.getAgeYears(), fishManuallyCreated.getAgeYears());
        assertEquals(fishCreatedByService.getPreferredFood(), fishManuallyCreated.getPreferredFood());
        assertEquals(fishCreatedByService.getId(), fishManuallyCreated.getId());
        assertEquals(fishCreatedByService.getDateOfPurchase(), fishManuallyCreated.getDateOfPurchase());
    }
}