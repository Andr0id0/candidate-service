package com.example.candidateservice.mapper.request;

import com.example.candidateservice.dto.request.DirectionCreateDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface DirectionCreateMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "candidates", source = "candidatesId", qualifiedByName = "mapCandidatesIdsToCandidates")
    @Mapping(target = "tests", source = "testsId", qualifiedByName = "mapTestsIdsToTests")
    Direction toEntity(DirectionCreateDto dto);


    @Named("mapCandidatesIdsToCandidates")
    default Set<Candidate> mapCandidatesIdsToCandidates(Set<Long> candidatesId) {
        if (candidatesId == null) {
            return null;
        }
        return candidatesId.stream()
                .map(id -> Candidate.builder().id(id).build())
                .collect(Collectors.toSet());
    }

    @Named("mapTestsIdsToTests")
    default Set<Test> mapTestsIdsToTests(Set<Long> testsId) {
        if (testsId == null) {
            return null;
        }
        return testsId.stream()
                .map(id -> Test.builder().id(id).build())
                .collect(Collectors.toSet());
    }

}
