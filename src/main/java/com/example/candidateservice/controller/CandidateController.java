package com.example.candidateservice.controller;


import com.example.candidateservice.dto.Response.CandidateResponseDto;
import com.example.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateService candidateService;


    @PostMapping("/create")
    private ResponseEntity<CandidateResponseDto> create(@RequestParam String lastName, @RequestParam String firstName,
                                                        @RequestParam String fatherName, @RequestParam MultipartFile photo,
                                                        @RequestParam String description, @RequestParam MultipartFile  CV,
                                                        @RequestParam(required = false) Set<Long> directionsId) throws IOException {
        CandidateResponseDto dto = candidateService.createCandidate(lastName, firstName, fatherName, photo, description, CV, directionsId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    private ResponseEntity<CandidateResponseDto> update(@RequestParam Long id, @RequestParam String lastName, @RequestParam String firstName,
                                             @RequestParam String fatherName, @RequestParam MultipartFile photo,
                                             @RequestParam String description, @RequestParam MultipartFile  CV,
                                             @RequestParam(required = false) Set<Long> directionsId) throws IOException {
        CandidateResponseDto candidate = candidateService.updateCandidate(id,lastName, firstName, fatherName, photo, description, CV, directionsId);
        return ResponseEntity.ok(candidate);
    }


    @GetMapping("/findById")
    private ResponseEntity<CandidateResponseDto> findById(@RequestParam Long id) {
        CandidateResponseDto candidate = candidateService.findCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<CandidateResponseDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "25") int size,
                                              @RequestParam(required = false) String sortField,
                                              @RequestParam(required = false) String sortType) {
        List<CandidateResponseDto> candidate = candidateService.findAllCandidates(page, size, sortField, sortType);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByLastName")
    private ResponseEntity<List<CandidateResponseDto>> findByLastName(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "25") int size,
                                                 @RequestParam(required = false) String sortField,
                                                 @RequestParam(required = false) String sortType,
                                                 @RequestParam String lastName)  {
        List<CandidateResponseDto> candidate = candidateService.findCandidateByLastName(page, size, sortField,
                sortType, lastName);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByFirstName")
    private ResponseEntity<List<CandidateResponseDto>> findByFirstName(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "25") int size,
                                                           @RequestParam(required = false) String sortField,
                                                           @RequestParam(required = false) String sortType,
                                                           @RequestParam String firstName)  {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByFirstName(page, size, sortField,
                sortType, firstName);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByFatherName")
    private ResponseEntity<List<CandidateResponseDto>> findByFatherName(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "25") int size,
                                                            @RequestParam(required = false) String sortField,
                                                            @RequestParam(required = false) String sortType,
                                                            @RequestParam String fatherName)  {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByFatherName(page, size, sortField,
                sortType, fatherName);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByPhoto")
    private ResponseEntity<List<CandidateResponseDto>> findByPhoto(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "25") int size,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortType,
                                                             @RequestParam MultipartFile photo) throws IOException {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByPhoto(page, size, sortField,
                sortType, photo);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByDescription")
    private ResponseEntity<List<CandidateResponseDto>> findByDescription(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "25") int size,
                                                        @RequestParam(required = false) String sortField,
                                                        @RequestParam(required = false) String sortType,
                                                        @RequestParam String description) throws IOException {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByDescription(page, size, sortField,
                sortType, description);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByCV")
    private ResponseEntity<List<CandidateResponseDto>> findByCV(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "25") int size,
                                                              @RequestParam(required = false) String sortField,
                                                              @RequestParam(required = false) String sortType,
                                                              @RequestParam MultipartFile CV) throws IOException {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByCV(page, size, sortField,
                sortType, CV);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/findByDirectionsId")
    private ResponseEntity<List<CandidateResponseDto>> findBy(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "25") int size,
                                                     @RequestParam(required = false) String sortField,
                                                     @RequestParam(required = false) String sortType,
                                                     @RequestParam Set<Long> directionsId) throws IOException {
        List<CandidateResponseDto> candidate = candidateService.findCandidatesByDirections(page, size, sortField,
                sortType, directionsId);
        return ResponseEntity.ok(candidate);
    }

}
