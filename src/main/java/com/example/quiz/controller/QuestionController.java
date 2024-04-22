package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    private ResponseEntity<List<Question>> fetchQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.fetchQuestions());
    }

    @PostMapping
    private ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.saveQuestion(question));
    }

    @GetMapping("{category}")
    private ResponseEntity<List<Question>> fetchQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.fetchQuestionsByCategory(category));
    }

}