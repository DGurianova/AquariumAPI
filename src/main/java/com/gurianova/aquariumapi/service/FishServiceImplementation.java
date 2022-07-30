package com.gurianova.aquariumapi.service;

import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.persistance.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishServiceImplementation implements FishService {
    @Autowired
    private FishRepository fishRepository;

    @Override
    public ResponseFishDTO createOrEditFish(RequestFishDTO request) {
        Fish fish = Fish.builder()
                .name(request.getName())
                .ageYears(request.getAgeYears())
                .preferredFood(request.getPreferredFood())
                .build();
        return convertToDTO(fishRepository.save(fish));
    }

    @Override
    public List<ResponseFishDTO> getAllFishes() {
        return fishRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ResponseFishDTO convertToDTO(Fish fish) {
        return ResponseFishDTO.builder()
                .id(fish.getId())
                .name(fish.getName())
                .ageYears(fish.getAgeYears())
                .preferredFood(fish.getPreferredFood())
                .build();

    }
}
