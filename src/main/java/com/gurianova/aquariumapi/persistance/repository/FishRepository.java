package com.gurianova.aquariumapi.persistance.repository;

import com.gurianova.aquariumapi.persistance.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Integer> {
}
