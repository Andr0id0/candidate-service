package com.example.candidateservice.mapper.response;

import com.example.candidateservice.dto.response.CandidateResponseDto;
import com.example.candidateservice.entity.Candidate;
import com.example.candidateservice.entity.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface CandidateResponseMapper {


    @Mapping(target = "photo", source = "photo", qualifiedByName = "byteArrayToTempFile")
    @Mapping(target = "CV", source = "CV", qualifiedByName = "byteArrayToTempFile")
    @Mapping(target = "directionsId", source = "directions", qualifiedByName = "directionToDirectionId")
    CandidateResponseDto toDto(Candidate candidate);

    @Named("byteArrayToTempFile")
    default File byteArrayToTempFile(byte[] byteArray) throws IOException {
        if (byteArray == null) {
            return null;
        }
        File tempFile = File.createTempFile("temp", ".tmp");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(byteArray);
        }
        return tempFile;
    }

    @Named("directionToDirectionId")
    default Set<Long> directionToDirectionId(Set<Direction> directions) {
        Set<Long> directionIds = new HashSet<>();
        for (Direction direction : directions) {
            directionIds.add(direction.getId());
        }
        return directionIds;
    }

}
