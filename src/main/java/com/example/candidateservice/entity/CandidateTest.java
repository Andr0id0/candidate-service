package com.example.candidateservice.entity;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;


@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer score;

}
