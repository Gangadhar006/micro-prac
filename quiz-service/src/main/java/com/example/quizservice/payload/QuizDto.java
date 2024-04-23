package com.example.quizservice.payload;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Long numQ;
    private String title;
}
