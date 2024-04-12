package com.example.candidateservice.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;


@Builder
@Table(name = "direction")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "directions")
    private Set<Candidate> candidates;

    @ManyToMany(mappedBy = "directions")
    private Set<Test> tests;
}
