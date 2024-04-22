package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final IQuestionRepository questionRepo;

    public List<Question> fetchQuestions() {
        return questionRepo.findAll();
    }

    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> fetchQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }
}
