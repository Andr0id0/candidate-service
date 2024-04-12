package com.example.candidateservice.dto.response;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionResponseDto {

    private Long id;

    private String name;

    private String description;

    private Set<Long> candidatesId;

    private Set<Long> testsId;

}
