package com.example.candidateservice.controller;

import com.example.candidateservice.dto.response.CandidateTestResponseDto;
import com.example.candidateservice.service.CandidateTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidateTest")
public class CandidateTestController {

    private final CandidateTestService candidateTestService;

    @PostMapping("/create")
    private ResponseEntity<CandidateTestResponseDto> create(@RequestParam Long candidateId,
                                                            @RequestParam Long testId,
                                                            @RequestParam Integer score) {
        CandidateTestResponseDto dto = candidateTestService.createCandidateTest(candidateId, testId, score);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    private ResponseEntity<CandidateTestResponseDto> update(@RequestParam Long id,
                                                            @RequestParam Long candidateId,
                                                            @RequestParam Long testId,
                                                            @RequestParam Integer score) {
        CandidateTestResponseDto dto = candidateTestService.updateCandidateTest(id ,candidateId, testId, score);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findById")
    private ResponseEntity<CandidateTestResponseDto> findById(@RequestParam Long id){
        CandidateTestResponseDto dto = candidateTestService.findCandidateTestById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<CandidateTestResponseDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "25") int size,
                                                          @RequestParam(required = false) String sortField,
                                                          @RequestParam(required = false) String sortType){
        List<CandidateTestResponseDto> dto = candidateTestService.findAllCandidateTest(page, size, sortField, sortType);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByCandidateId")
    private ResponseEntity<List<CandidateTestResponseDto>> findByCandidateId(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "25") int size,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortType,
                                                             @RequestParam Long candidateId){
        List<CandidateTestResponseDto> dto = candidateTestService.findCandidateTestByCandidateId(page, size, sortField, sortType, candidateId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByTestId")
    private ResponseEntity<List<CandidateTestResponseDto>> findByTestId(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "25") int size,
                                                                             @RequestParam(required = false) String sortField,
                                                                             @RequestParam(required = false) String sortType,
                                                                             @RequestParam Long testId){
        List<CandidateTestResponseDto> dto = candidateTestService.findCandidateTestByTestId(page, size, sortField, sortType, testId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByDate")
    private ResponseEntity<List<CandidateTestResponseDto>> findByDate(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "25") int size,
                                                                        @RequestParam(required = false) String sortField,
                                                                        @RequestParam(required = false) String sortType,
                                                                        @RequestParam LocalDate date){
        List<CandidateTestResponseDto> dto = candidateTestService.findCandidateTestByDate(page, size, sortField, sortType, date);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByScore")
    private ResponseEntity<List<CandidateTestResponseDto>> findByScore(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "25") int size,
                                                                      @RequestParam(required = false) String sortField,
                                                                      @RequestParam(required = false) String sortType,
                                                                      @RequestParam Integer score){
        List<CandidateTestResponseDto> dto = candidateTestService.findCandidateTestByScore(page, size, sortField, sortType, score);
        return ResponseEntity.ok(dto);
    }

}
