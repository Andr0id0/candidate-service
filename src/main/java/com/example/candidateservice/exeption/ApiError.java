package com.example.candidateservice.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class ApiError {

    private int status;

    private String message;

    private List<String> details;

}