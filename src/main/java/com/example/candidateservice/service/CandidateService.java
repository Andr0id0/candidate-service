package com.example.candidateservice.service;


import com.example.candidateservice.dto.CandidateCreateDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.mapper.CandidateCreateMapper;
import com.example.candidateservice.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    private final CandidateCreateMapper candidateCreateMapper;

    @Transactional
    public CandidateCreateDto createCandidate(CandidateCreateDto dto) {
        Candidate candidate = candidateCreateMapper.toEntity(dto);
        candidateRepository.save(candidate);
        return dto;
    }
}
