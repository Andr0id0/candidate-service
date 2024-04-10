package com.example.candidateservice.repository;

import com.example.candidateservice.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

    Set<Direction> findAllByIdIn(Set<Long> directionId);

}
