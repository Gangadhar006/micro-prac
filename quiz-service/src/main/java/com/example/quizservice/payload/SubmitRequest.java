package com.example.quizservice.payload;

import lombok.Data;

@Data
public class SubmitRequest {
    private Long qId;
    private String answer;
}
