package com.gurianova.aquariumapi.service;

import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.persistance.repository.FishRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishServiceImplementation implements FishService {
    @Autowired
    @Setter
    private FishRepository fishRepository;

    @Override
    public ResponseFishDTO createOrEditFish(RequestFishDTO request) {
        Fish.FishBuilder fishBuilder = Fish.builder();
        if ((request.getId() != null) && //true if id provided in request
                (fishRepository.findById(request.getId()).isPresent())) { //true if this id in the database
            fishBuilder.id(request.getId()); //fish with this id will be replaced
        }
        Fish fish = fishBuilder.name(request.getName())
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

    @Override
    public ResponseFishDTO convertToDTO(Fish fish) {
        return ResponseFishDTO.builder()
                .id(fish.getId())
                .name(fish.getName())
                .ageYears(fish.getAgeYears())
                .preferredFood(fish.getPreferredFood())
                .build();

    }
}
