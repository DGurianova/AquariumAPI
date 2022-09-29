package com.gurianova.aquariumapi.service;

import com.gurianova.aquariumapi.dto.RequestOwnerDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.dto.ResponseSearchFishDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import org.springframework.stereotype.Service;
import com.gurianova.aquariumapi.dto.RequestFishDTO;

import java.util.List;

@Service
public interface FishService {
    ResponseFishDTO createOrEditFish(RequestFishDTO request);

    List<ResponseFishDTO> getAllFishes();

    List<ResponseFishDTO> getFishByOwnerId(RequestOwnerDTO request);

    ResponseFishDTO convertToDTO(Fish fish);

    ResponseSearchFishDTO convertToSearchDTO(Fish fish);


}
