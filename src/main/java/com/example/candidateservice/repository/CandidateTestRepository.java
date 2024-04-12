package com.example.candidateservice.repository;

import com.example.candidateservice.entity.CandidateTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CandidateTestRepository extends JpaRepository<CandidateTest, Long> {

    List<CandidateTest> findByCandidateId(Long candidateId, Pageable pageable);

    List<CandidateTest> findByTestId(Long testId, Pageable pageable);

    List<CandidateTest> findByDate(LocalDate localDate, Pageable pageable);

    List<CandidateTest> findByScore(Integer score, Pageable pageable);

}
