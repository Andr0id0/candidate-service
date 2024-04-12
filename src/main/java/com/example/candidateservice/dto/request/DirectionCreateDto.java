package com.example.candidateservice.dto.request;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionCreateDto {

    private String name;

    private String description;

    private Set<Long> candidatesId;

    private Set<Long> testsId;

}
