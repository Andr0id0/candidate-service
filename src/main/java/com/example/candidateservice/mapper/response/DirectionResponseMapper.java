package com.example.candidateservice.mapper.response;

import com.example.candidateservice.dto.response.DirectionResponseDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface DirectionResponseMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "candidatesId", source = "candidates", qualifiedByName = "candidatesToCandidatesId")
    @Mapping(target = "testsId", source = "tests", qualifiedByName = "testsToTestsId")
    DirectionResponseDto toDto(Direction direction);


    @Named("candidatesToCandidatesId")
    default Set<Long> candidatesToCandidatesId(Set<Candidate> candidates) {
        Set<Long> candidatesIds = new HashSet<>();
        for (Candidate candidate : candidates) {
            candidatesIds.add(candidate.getId());
        }
        return candidatesIds;
    }
    @Named("testsToTestsId")
    default Set<Long> testsToTestsId(Set<Test> tests) {
        Set<Long> testsIds = new HashSet<>();
        for (Test test : tests) {
            testsIds.add(test.getId());
        }
        return testsIds;
    }
}
