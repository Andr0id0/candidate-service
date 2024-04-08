package com.example.candidateservice.repository;

import com.example.candidateservice.entity.CandidateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateTestRepository extends JpaRepository<CandidateTest, Long> {
}
