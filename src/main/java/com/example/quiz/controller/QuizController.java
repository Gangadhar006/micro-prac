package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.payload.QuestionWrapper;
import com.example.quiz.payload.SubmitRequest;
import com.example.quiz.service.QuizService;
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
    public ResponseEntity<List<Question>> createQuiz(@RequestParam String category,
                                                     @RequestParam Long numQ,
                                                     @RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.createQuiz(category, numQ, title));
    }

    @GetMapping("{quizId}")
    public ResponseEntity<List<QuestionWrapper>> fetchQuiz(@PathVariable Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.fetchQuiz(quizId));
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Long> submitQuiz(@RequestBody List<SubmitRequest> submitRequests,
                                                          @PathVariable Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.submitQuiz(submitRequests,quizId));
    }
}
