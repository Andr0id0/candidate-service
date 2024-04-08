package com.example.candidateservice.mapper;

import com.example.candidateservice.dto.CandidateCreateDto;
import com.example.candidateservice.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;


@Mapper(componentModel = "spring")
public interface CandidateCreateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "fatherName", source = "fatherName")
    @Mapping(target = "photo", source = "photo", qualifiedByName = "base64ToBytea")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "CV", source = "CV", qualifiedByName = "base64ToBytea")
    @Mapping(target = "directions", ignore = true)
    Candidate toEntity(CandidateCreateDto dto);

//    @Named("multipartFileToByteArr")
//    default byte[] multipartFileToByteArr(MultipartFile multipartFile) throws IOException {
//        return (multipartFile != null) ? multipartFile.getBytes() : null;
//    }

    @Named("base64ToBytea")
    default byte[] base64ToBytea(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
       return decodedBytes;
    }
}