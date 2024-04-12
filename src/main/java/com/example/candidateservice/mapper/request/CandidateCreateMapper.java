package com.example.candidateservice.mapper.request;

import com.example.candidateservice.dto.request.CandidateCreateDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CandidateCreateMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidateTest", ignore = true)
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "fatherName", source = "fatherName")
    @Mapping(target = "photo", source = "photo")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "CV", source = "CV")
    @Mapping(target = "directions", source = "directionsId", qualifiedByName = "mapDirectionsIdsToDirections")
    Candidate toEntity(CandidateCreateDto dto);


    @Named("mapDirectionsIdsToDirections")
    default Set<Direction> mapDirectionsIdsToDirections(Set<Long> directionsIds) {
        if (directionsIds == null) {
            return null;
        }
        return directionsIds.stream()
                .map(id -> Direction.builder().id(id).build())
                .collect(Collectors.toSet());
    }

}