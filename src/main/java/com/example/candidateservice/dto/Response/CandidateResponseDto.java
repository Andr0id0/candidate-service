package com.example.candidateservice.dto.Response;

import com.example.candidateservice.entity.Direction;
import lombok.*;

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

    private byte[] photo;

    private String description;

    private byte[] CV;

    private Set<Direction> directions;

}
