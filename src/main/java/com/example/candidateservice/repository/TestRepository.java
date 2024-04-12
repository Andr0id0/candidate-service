package com.example.candidateservice.repository;

import com.example.candidateservice.entity.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByName(String name, Pageable pageable);

    List<Test> findByDescription(String description, Pageable pageable);

    List<Test> findByDirections(Set<Long> directionsId, Pageable pageable);

    Set<Test> findAllByIdIn(Set<Long> testsId);

}
