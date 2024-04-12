package com.example.candidateservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Table
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "direction_test",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> directions;

    @OneToOne(mappedBy = "test")
    private CandidateTest candidateTest;

}
