package com.example.quizservice.feign;

import com.example.quizservice.payload.QuestionWrapper;
import com.example.quizservice.payload.SubmitRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizFeignInterface {
    @GetMapping("/api/question/generate")
    ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Long numQ);

    @PostMapping("/api/question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds);

    @PostMapping("/api/question/submit")
    ResponseEntity<Integer> getScore(@RequestBody List<SubmitRequest> submitRequests);
}