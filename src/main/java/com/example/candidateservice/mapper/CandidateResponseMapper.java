package com.example.candidateservice.mapper;

import com.example.candidateservice.dto.Response.CandidateResponseDto;
import com.example.candidateservice.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CandidateResponseMapper {


    @Mapping(target = "photo", source = "photo")
    @Mapping(target = "CV", source = "CV")
    CandidateResponseDto toDto(Candidate candidate);


}
