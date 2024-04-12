package com.example.candidateservice.service;

import com.example.candidateservice.dto.request.DirectionCreateDto;
import com.example.candidateservice.dto.response.DirectionResponseDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import com.example.candidateservice.exeption.ResourceNotFoundException;
import com.example.candidateservice.mapper.request.DirectionCreateMapper;
import com.example.candidateservice.mapper.response.DirectionResponseMapper;
import com.example.candidateservice.repository.CandidateRepository;
import com.example.candidateservice.repository.DirectionRepository;
import com.example.candidateservice.repository.TestRepository;
import com.example.candidateservice.util.SortAndPaginate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class DirectionService implements SortAndPaginate {

    private static final String DIRECTION_DOMAIN = "Direction";

    private final DirectionRepository directionRepository;

    private final CandidateRepository candidateRepository;

    private final TestRepository testRepository;

    private final DirectionResponseMapper directionResponseMapper;

    private final DirectionCreateMapper directionCreateMapper;

    @Transactional
    public DirectionResponseDto createDirection(String name, String description, Set<Long> candidatesId, Set<Long> testsId) {
        DirectionCreateDto directionCreateDto = new DirectionCreateDto(name, description, candidatesId, testsId);
        Direction direction = toEntity(directionCreateDto);
        Set<Candidate> candidates = candidateRepository.findAllByIdIn(candidatesId);
        direction.setCandidates(candidates);
        Set<Test> tests = testRepository.findAllByIdIn(testsId);
        direction.setTests(tests);
        return toDto(directionRepository.save(direction));
    }

    @Transactional
    public DirectionResponseDto updateDirection(Long id, String name, String description, Set<Long> candidatesId, Set<Long> testsId) {
        Optional<Direction> direction = directionRepository.findById(id);
        if (direction.isPresent()) {
            Direction updatetDirection = direction.get();
            updatetDirection.setName(name);
            updatetDirection.setDescription(description);
            Set<Candidate> candidates = candidateRepository.findAllByIdIn(candidatesId);
            Set<Test> tests = testRepository.findAllByIdIn(testsId);
            updatetDirection.setCandidates(candidates);
            updatetDirection.setTests(tests);
            return toDto(directionRepository.save(updatetDirection));
        }
        else throw new ResourceNotFoundException(DIRECTION_DOMAIN, id);
    }

    @Transactional
    public DirectionResponseDto findDirectionById(Long id) {
        Optional<Direction> direction = directionRepository.findById(id);
        if (direction.isPresent()) {
            return toDto(direction.get());
        }
        else throw new ResourceNotFoundException(DIRECTION_DOMAIN, id);
    }

    @Transactional
    public List<DirectionResponseDto> findAllDirections(int pageNumber, int pageSize, String sortField, String sortType) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(directionRepository.findAll(pageable).getContent());
    }

    @Transactional
    public List<DirectionResponseDto> findDirectionByName(int pageNumber, int pageSize, String sortField, String sortType, String name) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(directionRepository.findByName(name, pageable));
    }

    @Transactional
    public List<DirectionResponseDto> findDirectionByDescription(int pageNumber, int pageSize, String sortField, String sortType, String description) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(directionRepository.findByDescription(description, pageable));
    }

    @Transactional
    public List<DirectionResponseDto> findDirectionByCandidates(int pageNumber, int pageSize, String sortField, String sortType, Set<Long> candidatesId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(directionRepository.findByCandidates(candidatesId, pageable));
    }

    @Transactional
    public List<DirectionResponseDto> findDirectionByTests(int pageNumber, int pageSize, String sortField, String sortType, Set<Long> testsId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(directionRepository.findByTests(testsId, pageable));
    }

    private DirectionResponseDto toDto(Direction direction) {
        return directionResponseMapper.toDto(direction);
    }

    private List<DirectionResponseDto> toDtoList(List<Direction> directions) {
        return directions.stream().map(directionResponseMapper::toDto).toList();
    }

    private Direction toEntity(DirectionCreateDto directionCreateDto) {
        return directionCreateMapper.toEntity(directionCreateDto);
    }

}
