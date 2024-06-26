package com.example.candidateservice.service;

import com.example.candidateservice.dto.request.CandidateCreateDto;
import com.example.candidateservice.dto.response.CandidateResponseDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.exeption.ResourceNotFoundException;
import com.example.candidateservice.mapper.request.CandidateCreateMapper;
import com.example.candidateservice.mapper.response.CandidateResponseMapper;
import com.example.candidateservice.repository.CandidateRepository;
import com.example.candidateservice.repository.DirectionRepository;
import com.example.candidateservice.util.SortAndPaginate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CandidateService implements SortAndPaginate {

    private static final String CANDIDATE_DOMAIN = "Candidate";

    private final CandidateRepository candidateRepository;

    private final CandidateCreateMapper candidateCreateMapper;

    private final DirectionRepository directionRepository;

    private final CandidateResponseMapper candidateResponseMapper;

    @Transactional
    public CandidateResponseDto createCandidate(String lastName, String firstName, String fatherName, MultipartFile photo,
                                              String description, MultipartFile CV, Set<Long> directionsId) throws IOException {
        CandidateCreateDto candidateCreateDto  = new CandidateCreateDto(lastName, firstName, fatherName, photo.getBytes(), description, CV.getBytes(), directionsId);
        Candidate candidate = toEntity(candidateCreateDto);
        Set<Direction> directions = directionRepository.findAllByIdIn(directionsId);
        candidate.setDirections(directions);
        return toDto(candidateRepository.save(candidate));

    }

    @Transactional
    public CandidateResponseDto updateCandidate(Long id, String lastName, String firstName, String fatherName,
                                     MultipartFile photo, String description, MultipartFile CV, Set<Long> directionsId) throws IOException {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            Candidate updatetCandidate = candidate.get();
            updatetCandidate.setLastName(lastName);
            updatetCandidate.setFirstName(firstName);
            updatetCandidate.setFatherName(fatherName);
            updatetCandidate.setPhoto(photo.getBytes());
            updatetCandidate.setDescription(description);
            updatetCandidate.setCV(CV.getBytes());
            Set<Direction> directions = directionRepository.findAllByIdIn(directionsId);
            updatetCandidate.setDirections(directions);
            return toDto(candidateRepository.save(updatetCandidate));
        }
        else throw new ResourceNotFoundException(CANDIDATE_DOMAIN, id);
    }

    @Transactional
    public CandidateResponseDto findCandidateById(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return toDto(candidate.get());
        }
        else throw new ResourceNotFoundException(CANDIDATE_DOMAIN, id);
    }

    @Transactional
    public List<CandidateResponseDto> findAllCandidates(int pageNumber, int pageSize, String sortField, String sortType) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findAll(pageable).getContent());
    }

    @Transactional
    public List<CandidateResponseDto> findCandidateByLastName(int pageNumber, int pageSize, String sortField, String sortType, String lastName) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByLastName(lastName, pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByFirstName(int pageNumber, int pageSize, String sortField, String sortType, String firstName) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByFirstName(firstName, pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByFatherName(int pageNumber, int pageSize, String sortField, String sortType, String fatherName) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByFatherName(fatherName, pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByPhoto(int pageNumber, int pageSize, String sortField, String sortType, MultipartFile photo) throws IOException {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByPhoto(photo.getBytes(), pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByDescription(int pageNumber, int pageSize, String sortField, String sortType, String description) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByDescription(description, pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByCV(int pageNumber, int pageSize, String sortField, String sortType, MultipartFile CV) throws IOException {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByCV(CV.getBytes(), pageable));
    }

    @Transactional
    public List<CandidateResponseDto> findCandidatesByDirections(int pageNumber, int pageSize, String sortField, String sortType, Set<Long> directionsId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(candidateRepository.findByDirections(directionsId, pageable));
    }

    private CandidateResponseDto toDto(Candidate candidate) {
        return candidateResponseMapper.toDto(candidate);
    }

    private List<CandidateResponseDto> toDtoList(List<Candidate> candidate) {
        return candidate.stream().map(candidateResponseMapper::toDto).toList();
    }

    private Candidate toEntity(CandidateCreateDto candidateCreateDto) {
        return candidateCreateMapper.toEntity(candidateCreateDto);
    }

}
