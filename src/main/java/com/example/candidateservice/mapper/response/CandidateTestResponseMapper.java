package com.example.candidateservice.mapper.response;

import com.example.candidateservice.dto.response.CandidateTestResponseDto;
import com.example.candidateservice.entity.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateTestResponseMapper {

    @Mapping(target = "testId", source = "test.id")
    @Mapping(target = "candidateId", source = "candidate.id")
    CandidateTestResponseDto toDto(CandidateTest candidateTest);

}
