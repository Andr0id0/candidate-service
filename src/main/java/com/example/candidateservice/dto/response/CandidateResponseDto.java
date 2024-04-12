package com.example.candidateservice.dto.response;

import lombok.*;

import java.io.File;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String fatherName;

    private File photo;

    private String description;

    private File CV;

    private Set<Long> directionsId;

}
