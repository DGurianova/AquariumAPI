package com.gurianova.aquariumapi.persistance.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "owner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;
    @Column(name = "owner_name")
    private String name;
    @Column(name = "owner_second_name")
    private String secondName;
    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;
    @Column(name = "address")
    private String address;
}
