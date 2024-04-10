package com.example.candidateservice.dto.Request;


import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCreateDto {

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @NotNull
    private String fatherName;

    private byte[]  photo;

    private String description;

    private byte[]  CV;

    private Set<Long> directionsId;

}
