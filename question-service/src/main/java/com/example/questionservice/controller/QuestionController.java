package com.example.questionservice.controller;

import com.example.questionservice.model.Question;
import com.example.questionservice.payload.QuestionWrapper;
import com.example.questionservice.payload.SubmitRequest;
import com.example.questionservice.service.QuestionService;
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

    // generate
    // getQuestionbyId
    // calculate score

    @GetMapping("/generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String category,
                                                          @RequestParam Long numQ) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsForQuiz(category, numQ));
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsFromIds(questionIds));
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> getScore(@RequestBody List<SubmitRequest> submitRequests) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getScore(submitRequests));
    }
}