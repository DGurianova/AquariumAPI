package com.gurianova.aquariumapi.controller;


import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class FishController {
    @Autowired
    private FishService fishService;

    @Autowired
    private EntityManager manager;


    @PutMapping(value = "/fishes", consumes = "application/json", produces = "application/json")
    public ResponseFishDTO createOrEditFish(@RequestBody RequestFishDTO request) {
        return fishService.createOrEditFish(request);
    }

    @GetMapping(value = "/fishes", produces = "application/json")
    public List<ResponseFishDTO> getAllFishes() {

        return fishService.getAllFishes();
    }

    @PostMapping(value = "/fishes/search", produces = "application/json")
    public List<ResponseFishDTO> searchFish(@RequestBody RequestFishDTO request) {
        String baseQuery = "select * from fish f where";
        if (request.getName() != null) {
            baseQuery = baseQuery + "  fish_name  = '" + request.getName() + "'";
        }
        if (request.getAgeYears() != null) {
            baseQuery = baseQuery + " and fish_age_years  = " + request.getAgeYears() + "";
        }
        if (request.getPreferredFood() != null) {
            baseQuery = baseQuery + " and preferred_food  = '" + request.getPreferredFood() + "'";
        }
        TypedQuery<Fish> query = manager.createQuery(baseQuery, Fish.class);
        return query.getResultList()
                .stream()
                .map(fishService::convertToDTO)
                .collect(Collectors.toList());
    }
}
