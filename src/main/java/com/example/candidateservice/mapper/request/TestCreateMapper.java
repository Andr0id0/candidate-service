package com.example.candidateservice.mapper.request;

import com.example.candidateservice.dto.request.TestCreateDto;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface TestCreateMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidateTest", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "directions", source = "directionsId", qualifiedByName = "mapDirectionsIdsToDirections")
    Test toEntity(TestCreateDto dto);


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
