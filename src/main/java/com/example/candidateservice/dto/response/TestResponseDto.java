package com.example.candidateservice.dto.response;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResponseDto {

    private Long id;

    private String name;

    private String description;

    private Set<Long> directionsId;

}
