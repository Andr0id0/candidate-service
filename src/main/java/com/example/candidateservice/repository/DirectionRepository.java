package com.example.candidateservice.repository;

import com.example.candidateservice.entity.Direction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface DirectionRepository extends JpaRepository<Direction, Long> {

    List<Direction> findByName(String name, Pageable pageable);

    List<Direction> findByDescription(String description, Pageable pageable);

    List<Direction> findByCandidates(Set<Long> candidatesId, Pageable pageable);

    List<Direction> findByTests(Set<Long> testsId, Pageable pageable);

    Set<Direction> findAllByIdIn(Set<Long> directionId);

}
