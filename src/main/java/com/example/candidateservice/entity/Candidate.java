package com.example.candidateservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Table(name = "candidate")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String fatherName;

    private byte[] photo;

    @Column(nullable = false)
    private String description;

    private byte[] CV;

    @ManyToMany
    @JoinTable(
            name = "direction_candidate",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> directions;

    @OneToOne(mappedBy = "candidate")
    private CandidateTest candidateTest;

}
