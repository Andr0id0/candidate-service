package com.example.candidateservice.dto.request;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTestCreateDto {

    private Long candidateId;

    private Long testId;

    private LocalDate date;

    private Integer score;

}
