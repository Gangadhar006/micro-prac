package com.example.quizservice.service;

import com.example.quizservice.feign.QuizFeignInterface;
import com.example.quizservice.model.Question;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.payload.QuestionWrapper;
import com.example.quizservice.payload.SubmitRequest;
import com.example.quizservice.repository.IQuizRespository;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final IQuizRespository quizRepo;

    @Autowired
    private QuizFeignInterface quizFeignInterface;

    public List<Long> createQuiz(String category, Long numQ, String title) {
        List<Long> questionsIds = quizFeignInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionsIds);
        quizRepo.save(quiz);
        return questionsIds;
    }

    public List<QuestionWrapper> fetchQuiz(Long quizId) {
        Quiz quiz = quizRepo.findById(quizId).get();
        List<Long> questionIds = quiz.getQuestions();
        return quizFeignInterface.getQuestions(questionIds).getBody();
    }

    public Long submitQuiz(List<SubmitRequest> submitRequests) {
        return Long.valueOf(quizFeignInterface.getScore(submitRequests).getBody());
    }
}