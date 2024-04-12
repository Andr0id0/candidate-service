package com.example.candidateservice.controller;

import com.example.candidateservice.dto.response.TestResponseDto;
import com.example.candidateservice.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @PostMapping("/create")
    private ResponseEntity<TestResponseDto> create(@RequestParam String name, @RequestParam String description,
                                                   @RequestParam(required = false) Set<Long> directionsId) {
        TestResponseDto dto = testService.createTest(name, description, directionsId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    private ResponseEntity<TestResponseDto> update(@RequestParam Long id, @RequestParam String name,
                                                   @RequestParam String description, @RequestParam(required = false) Set<Long> directionsId) {
        TestResponseDto dto = testService.updateTest(id ,name, description, directionsId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findById")
    private ResponseEntity<TestResponseDto> findById(@RequestParam Long id){
        TestResponseDto dto = testService.findTestById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<TestResponseDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "25") int size,
                                                          @RequestParam(required = false) String sortField,
                                                          @RequestParam(required = false) String sortType){
        List<TestResponseDto> dto = testService.findAllTests(page, size, sortField, sortType);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByName")
    private ResponseEntity<List<TestResponseDto>> findByName(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "25") int size,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortType,
                                                             @RequestParam String name){
        List<TestResponseDto> dto = testService.findTestByName(page, size, sortField, sortType, name);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByDescription")
    private ResponseEntity<List<TestResponseDto>> findByDescription(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "25") int size,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortType,
                                                             @RequestParam String description){
        List<TestResponseDto> dto = testService.findTestByDescription(page, size, sortField, sortType, description);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByDirectionsId")
    private ResponseEntity<List<TestResponseDto>> findByDirectionsId(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "25") int size,
                                                                    @RequestParam(required = false) String sortField,
                                                                    @RequestParam(required = false) String sortType,
                                                                    @RequestParam Set<Long> directionsId){
        List<TestResponseDto> dto = testService.findTestsByDirections(page, size, sortField, sortType, directionsId);
        return ResponseEntity.ok(dto);
    }

}
