package com.example.candidateservice.service;

import com.example.candidateservice.dto.request.CandidateTestCreateDto;
import com.example.candidateservice.dto.response.CandidateTestResponseDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.CandidateTest;
import com.example.candidateservice.entity.Test;
import com.example.candidateservice.mapper.request.CandidateTestCreateMapper;
import com.example.candidateservice.mapper.response.CandidateTestResponseMapper;
import com.example.candidateservice.repository.CandidateRepository;
import com.example.candidateservice.repository.CandidateTestRepository;
import com.example.candidateservice.repository.TestRepository;
import com.example.candidateservice.util.SortAndPaginate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CandidateTestService implements SortAndPaginate {

    private final CandidateTestRepository candidateTestRepository;

    private final TestRepository testRepository;

    private final CandidateRepository candidateRepository;

    private final CandidateTestResponseMapper candidateTestResponseMapper;

    private final CandidateTestCreateMapper candidateTestCreateMapper;

    @Transactional
    public CandidateTestResponseDto createCandidateTest(Long candidateId, Long testId, Integer score) {
        CandidateTestCreateDto candidateTestCreateDto = new CandidateTestCreateDto(candidateId, testId, LocalDate.now(), score);
        CandidateTest candidateTest = toEntity(candidateTestCreateDto);
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        Optional<Test> test = testRepository.findById(testId);
        if (candidate.isPresent() && test.isPresent()) {
            candidateTest.setCandidate(candidate.get());
            candidateTest.setTest(test.get());
        } else {
            throw new NoSuchElementException();
        }
        return toDto(candidateTestRepository.save(candidateTest));
    }

    @Transactional
    public CandidateTestResponseDto updateCandidateTest(Long id, Long candidateId, Long testId, Integer score) {
        Optional<CandidateTest> candidateTest = candidateTestRepository.findById(id);
        if (candidateTest.isPresent()) {
            CandidateTest updatetCandiateTest = candidateTest.get();
            Optional<Candidate> candidate = candidateRepository.findById(candidateId);
            Optional<Test> test = testRepository.findById(testId);
            if (candidate.isPresent() && test.isPresent()) {
                updatetCandiateTest.setCandidate(candidate.get());
                updatetCandiateTest.setTest(test.get());
            } else {
                throw new NoSuchElementException();
            }
            updatetCandiateTest.setDate(LocalDate.now());
            updatetCandiateTest.setScore(score);
            return toDto(candidateTestRepository.save(updatetCandiateTest));
        }
        else throw new NoSuchElementException();
    }

    @Transactional
    public CandidateTestResponseDto findCandidateTestById(Long id) {
        Optional<CandidateTest> candidateTest = candidateTestRepository.findById(id);
        if (candidateTest.isPresent()) {
            return toDto(candidateTest.get());
        }
        else throw new NoSuchElementException();
    }

    @Transactional
    public List<CandidateTestResponseDto> findAllCandidateTest(int pageNumber, int pageSize, String sortField, String sortType) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateTestRepository.findAll(pageable).getContent());
    }

    @Transactional
    public List<CandidateTestResponseDto> findCandidateTestByCandidateId(int pageNumber, int pageSize, String sortField, String sortType, Long candidateId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateTestRepository.findByCandidateId(candidateId, pageable));
    }

    @Transactional
    public List<CandidateTestResponseDto> findCandidateTestByTestId(int pageNumber, int pageSize, String sortField, String sortType, Long testId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateTestRepository.findByTestId(testId, pageable));
    }

    @Transactional
    public List<CandidateTestResponseDto> findCandidateTestByDate(int pageNumber, int pageSize, String sortField, String sortType, LocalDate date) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateTestRepository.findByDate(date, pageable));
    }

    @Transactional
    public List<CandidateTestResponseDto> findCandidateTestByScore(int pageNumber, int pageSize, String sortField, String sortType, Integer score) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateTestRepository.findByScore(score, pageable));
    }


    private CandidateTestResponseDto toDto(CandidateTest candidateTest) {
        return candidateTestResponseMapper.toDto(candidateTest);
    }

    private List<CandidateTestResponseDto> toDtoList(List<CandidateTest> candidateTests) {
        return candidateTests.stream().map(candidateTestResponseMapper::toDto).toList();
    }

    private CandidateTest toEntity(CandidateTestCreateDto testCreateDto) {
        return candidateTestCreateMapper.toEntity(testCreateDto);
    }

}
