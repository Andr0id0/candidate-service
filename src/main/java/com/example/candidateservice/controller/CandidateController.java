package com.example.candidateservice.controller;


import com.example.candidateservice.dto.CandidateCreateDto;
import com.example.candidateservice.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateService candidateService;


    @PostMapping("/create")
    private ResponseEntity<CandidateCreateDto> create(@RequestBody CandidateCreateDto dto) {
        candidateService.createCandidate(dto);
        return ResponseEntity.ok(dto);
    }

}
