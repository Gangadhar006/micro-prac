package com.example.quizservice.controller;

import com.example.quizservice.model.Question;
import com.example.quizservice.payload.QuestionWrapper;
import com.example.quizservice.payload.QuizDto;
import com.example.quizservice.payload.SubmitRequest;
import com.example.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<List<Long>> createQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle()));
    }

    @GetMapping("{quizId}")
    public ResponseEntity<List<QuestionWrapper>> fetchQuiz(@PathVariable Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.fetchQuiz(quizId));
    }

    @PostMapping("/submit")
    public ResponseEntity<Long> submitQuiz(@RequestBody List<SubmitRequest> submitRequests) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.submitQuiz(submitRequests));
    }
}
