package com.example.candidateservice.service;

import com.example.candidateservice.dto.request.TestCreateDto;
import com.example.candidateservice.dto.response.TestResponseDto;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import com.example.candidateservice.exeption.ResourceNotFoundException;
import com.example.candidateservice.mapper.request.TestCreateMapper;
import com.example.candidateservice.mapper.response.TestResponseMapper;
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
public class TestService implements SortAndPaginate {

    private static final String TEST_DOMAIN = "Test";

    private final TestRepository testRepository;

    private final DirectionRepository directionRepository;

    private final TestCreateMapper testCreateMapper;

    private final TestResponseMapper testResponseMapper;

    @Transactional
    public TestResponseDto createTest(String name, String description, Set<Long> directionsId) {
        TestCreateDto testCreateDto = new TestCreateDto(name, description, directionsId);
        Test test = toEntity(testCreateDto);
        Set<Direction> directions = directionRepository.findAllByIdIn(directionsId);
        test.setDirections(directions);
        return toDto(testRepository.save(test));
    }

    @Transactional
    public TestResponseDto updateTest(Long id, String name, String description, Set<Long> directionsId) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            Test updatetTest = test.get();
            updatetTest.setName(name);
            updatetTest.setDescription(description);
            Set<Direction> directions = directionRepository.findAllByIdIn(directionsId);
            updatetTest.setDirections(directions);
            return toDto(testRepository.save(updatetTest));
        }
        else throw new ResourceNotFoundException(TEST_DOMAIN, id);
    }

    @Transactional
    public TestResponseDto findTestById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            return toDto(test.get());
        }
        else throw new ResourceNotFoundException(TEST_DOMAIN, id);
    }

    @Transactional
    public List<TestResponseDto> findAllTests(int pageNumber, int pageSize, String sortField, String sortType) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(testRepository.findAll(pageable).getContent());
    }

    @Transactional
    public List<TestResponseDto> findTestByName(int pageNumber, int pageSize, String sortField, String sortType, String name) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(testRepository.findByName(name, pageable));
    }

    @Transactional
    public List<TestResponseDto> findTestByDescription(int pageNumber, int pageSize, String sortField, String sortType, String description) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(testRepository.findByDescription(description, pageable));
    }

    @Transactional
    public List<TestResponseDto> findTestsByDirections(int pageNumber, int pageSize, String sortField, String sortType, Set<Long> directionsId) {
        var pageable = getCustomPageRequest(pageNumber, pageSize, sortField, sortType);
        return toDtoList(testRepository.findByDirections(directionsId, pageable));
    }

    private TestResponseDto toDto(Test test) {
        return testResponseMapper.toDto(test);
    }

    private List<TestResponseDto> toDtoList(List<Test> tests) {
        return tests.stream().map(testResponseMapper::toDto).toList();
    }

    private Test toEntity(TestCreateDto testCreateDto) {
        return testCreateMapper.toEntity(testCreateDto);
    }

}
