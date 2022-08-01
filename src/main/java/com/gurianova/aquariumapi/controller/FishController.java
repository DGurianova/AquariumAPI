package com.gurianova.aquariumapi.controller;


import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.RequestSearchFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.dto.ResponseSearchFishDTO;
import com.gurianova.aquariumapi.dto.ResponseSearchFishErrorDTO;
import com.gurianova.aquariumapi.exception.AquariumErrorCodes;
import com.gurianova.aquariumapi.exception.ServedByOtherMethodException;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import com.gurianova.aquariumapi.service.FishService;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<ResponseSearchFishDTO> searchFish(@RequestBody RequestSearchFishDTO request) {
        String baseQuery = "SELECT f FROM Fish f WHERE";
        boolean queryParameterAlreadyExists = false;
        if (request.getName() != null) {
            baseQuery = baseQuery + "  fish_name  = '" + request.getName() + "'";
            queryParameterAlreadyExists = true;
        }
        if (request.getAgeYears() != null) {
            if (queryParameterAlreadyExists) {
                baseQuery = baseQuery + " and ";
            } //If queryParameterAlreadyExists is  true , getName  was not null and was already added to query , so we need to add additional AND before add next parameter
            baseQuery = baseQuery + " fish_age_years  = " + request.getAgeYears() + "";
            queryParameterAlreadyExists = true;                                  //Because we just added new param with AND , we need to set this flag to true , to signal the next if to add AND
        }
        if (request.getPreferredFood() != null) {
            if (queryParameterAlreadyExists) {
                baseQuery = baseQuery + " and ";
            }//If queryParameterAlreadyExists is true , getAgeYears() was not null and was already added to query , so we need to add additional AND before add next parameter
            baseQuery = baseQuery + " preferred_food  = '" + request.getPreferredFood() + "'";
            queryParameterAlreadyExists = true;
        }
        if (request.getDateOfPurchase() != null) {
            if (queryParameterAlreadyExists) {
                baseQuery = baseQuery + " and ";
            }
            baseQuery = baseQuery + " date_of_purchase  = '" + request.getDateOfPurchase() + "'";
        }

        try {
            TypedQuery<Fish> query = manager.createQuery(baseQuery, Fish.class);
            List<ResponseSearchFishDTO> collect;
            collect = query.getResultList()
                    .stream()
                    .map(fishService::convertToSearchDTO)
                    .collect(Collectors.toList());
            return collect;
        } catch (IllegalArgumentException e) {
            ResponseSearchFishErrorDTO errorResponse = new ResponseSearchFishErrorDTO(
                    AquariumErrorCodes.SEARCH_FISH_PAYLOAD_HAS_NO_PARAMETERS.getCode(),
                    AquariumErrorCodes.SEARCH_FISH_PAYLOAD_HAS_NO_PARAMETERS.getDescription()
            );
            List<ResponseSearchFishDTO> response = Collections.singletonList((ResponseSearchFishDTO) errorResponse);
            return response;
        }
        }
    }

