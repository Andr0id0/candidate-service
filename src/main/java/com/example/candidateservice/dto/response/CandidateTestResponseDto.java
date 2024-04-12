package com.example.candidateservice.dto.response;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTestResponseDto {

    private Long candidateId;

    private Long testId;

    private LocalDate date;

    private Integer score;

}
