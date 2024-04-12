package com.example.candidateservice.controller;

import com.example.candidateservice.dto.response.DirectionResponseDto;
import com.example.candidateservice.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/direction")
public class DirectionController {

    private final DirectionService directionService;

    @PostMapping("/create")
    private ResponseEntity<DirectionResponseDto> create(@RequestParam String name, @RequestParam String description,
                                                        @RequestParam(required = false) Set<Long> candidatesId,
                                                        @RequestParam(required = false) Set<Long> testsId) {
        DirectionResponseDto dto = directionService.createDirection(name, description, candidatesId, testsId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    private ResponseEntity<DirectionResponseDto> update(@RequestParam Long id, @RequestParam String name,
                                                        @RequestParam String description,
                                                        @RequestParam(required = false) Set<Long> candidatesId,
                                                        @RequestParam(required = false) Set<Long> testsId) {
        DirectionResponseDto direction = directionService.updateDirection(id, name, description, candidatesId, testsId);
        return ResponseEntity.ok(direction);
    }


    @GetMapping("/findById")
    private ResponseEntity<DirectionResponseDto> findById(@RequestParam Long id) {
        DirectionResponseDto direction = directionService.findDirectionById(id);
        return ResponseEntity.ok(direction);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<DirectionResponseDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "25") int size,
                                                               @RequestParam(required = false) String sortField,
                                                               @RequestParam(required = false) String sortType) {
        List<DirectionResponseDto> direction = directionService.findAllDirections(page, size, sortField, sortType);
        return ResponseEntity.ok(direction);
    }

    @GetMapping("/findByLastName")
    private ResponseEntity<List<DirectionResponseDto>> findByName(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "25") int size,
                                                                      @RequestParam(required = false) String sortField,
                                                                      @RequestParam(required = false) String sortType,
                                                                      @RequestParam String name)  {
        List<DirectionResponseDto> direction = directionService.findDirectionByName(page, size, sortField,
                sortType, name);
        return ResponseEntity.ok(direction);
    }

    @GetMapping("/findByDescription")
    private ResponseEntity<List<DirectionResponseDto>> findByDescription(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "25") int size,
                                                                  @RequestParam(required = false) String sortField,
                                                                  @RequestParam(required = false) String sortType,
                                                                  @RequestParam String description)  {
        List<DirectionResponseDto> direction = directionService.findDirectionByDescription(page, size, sortField,
                sortType, description);
        return ResponseEntity.ok(direction);
    }

    @GetMapping("/findByCandidatesId")
    private ResponseEntity<List<DirectionResponseDto>> findByCandidatesId(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "25") int size,
                                                                          @RequestParam(required = false) String sortField,
                                                                          @RequestParam(required = false) String sortType,
                                                                          @RequestParam Set<Long> candidatesId) {
        List<DirectionResponseDto> direction = directionService.findDirectionByCandidates(page, size, sortField,
                sortType, candidatesId);
        return ResponseEntity.ok(direction);
    }

    @GetMapping("/findByTestsId")
    private ResponseEntity<List<DirectionResponseDto>> findByTestsId(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "25") int size,
                                                                          @RequestParam(required = false) String sortField,
                                                                          @RequestParam(required = false) String sortType,
                                                                          @RequestParam Set<Long> testsId) {
        List<DirectionResponseDto> direction = directionService.findDirectionByCandidates(page, size, sortField,
                sortType, testsId);
        return ResponseEntity.ok(direction);
    }

}
