package com.example.candidateservice.repository;

import com.example.candidateservice.entity.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByLastName(String lastName, Pageable pageable);

    List<Candidate> findByFirstName(String firstName, Pageable pageable);

    List<Candidate> findByFatherName(String fatherName, Pageable pageable);

    List<Candidate> findByPhoto(byte[] photo, Pageable pageable);

    List<Candidate> findByDescription(String description, Pageable pageable);

    List<Candidate> findByCV(byte[] CV, Pageable pageable);

    List<Candidate> findByDirections(Set<Long> directionsId, Pageable pageable);

    Set<Candidate> findAllByIdIn(Set<Long> candidateId);

}
