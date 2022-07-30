package com.gurianova.aquariumapi.controller;


import com.gurianova.aquariumapi.dto.RequestFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FishController {
    @Autowired
    private FishService fishService;

    @PutMapping(value = "/fishes", consumes = "application/json", produces = "application/json")
    public ResponseFishDTO createOrEditFish(@RequestBody RequestFishDTO request) {
       return fishService.createOrEditFish(request);
    }

    @GetMapping(value = "/fishes",produces = "application/json")
    public List<ResponseFishDTO> getAllFishes() {
        return fishService.getAllFishes();
    }
}
