package com.example.candidateservice.mapper.response;

import com.example.candidateservice.dto.response.TestResponseDto;
import com.example.candidateservice.entity.Direction;
import com.example.candidateservice.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface TestResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "directionsId", source = "directions", qualifiedByName = "directionToDirectionId")
    TestResponseDto toDto(Test test);

    @Named("directionToDirectionId")
    default Set<Long> directionToDirectionId(Set<Direction> directions) {
        Set<Long> directionIds = new HashSet<>();
        for (Direction direction : directions) {
            directionIds.add(direction.getId());
        }
        return directionIds;
    }

}
