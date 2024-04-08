package com.example.candidateservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@Builder
public class CandidateCreateDto {

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    private String fatherName;

    private String photo;

    private String description;

    private String CV;

    private Set<Long> directions;

}
