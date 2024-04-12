package com.example.candidateservice.mapper.request;

import com.example.candidateservice.dto.request.CandidateTestCreateDto;
import com.example.candidateservice.entity.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CandidateTestCreateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "test.id", source = "testId")
    @Mapping(target = "candidate.id", source = "candidateId")
    CandidateTest toEntity(CandidateTestCreateDto dto);
}
