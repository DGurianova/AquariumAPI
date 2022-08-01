package com.gurianova.aquariumapi.persistance.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fish")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fish_id")
    private Integer id;
    @Column(name = "fish_name")
    private String name;
    @Column(name = "fish_age_years")
    private Integer ageYears;
    @Column(name = "preferred_food")
    private String preferredFood;
    @Column(name = "date_of_purchase")
    private String dateOfPurchase;
}
