package com.example.candidateservice.mapper;

import com.example.candidateservice.dto.Request.CandidateCreateDto;
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
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "fatherName", source = "fatherName")
    @Mapping(target = "photo", source = "photo")//, qualifiedByName = "multipartFileToByteArr")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "CV", source = "CV") //, qualifiedByName = "multipartFileToByteArr")
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
//    @Mapping(target = "directions", source = "directions", qualifiedByName = "convertLongsToDirections")

//    @Named("multipartFileToByteArr")
//    default byte[] multipartFileToByteArr(MultipartFile multipartFile) throws IOException {
//        return (multipartFile != null) ? multipartFile.getBytes() : null;
//    }
//
//    @Named("convertToByteArray")
//    default byte[] convertToByteArray(File file) throws IOException {
//        FileInputStream fis = new FileInputStream(file);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        while ((bytesRead = fis.read(buffer)) != -1) {
//            bos.write(buffer, 0, bytesRead);
//        }
//        fis.close();
//        bos.close();
//        return bos.toByteArray();
//    }
//
//    @Named("base64ToBytea")
//    default byte[] base64ToBytea(String base64String) {
//        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
//       return decodedBytes;
//    }
}