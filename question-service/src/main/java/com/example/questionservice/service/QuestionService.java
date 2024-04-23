package com.example.questionservice.service;

import com.example.questionservice.model.Question;
import com.example.questionservice.payload.QuestionWrapper;
import com.example.questionservice.payload.SubmitRequest;
import com.example.questionservice.repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Long> getQuestionsForQuiz(String category, Long numQ) {
        return questionRepo.fetchRandomQuestionsByCategory(category, numQ);

    }

    public List<QuestionWrapper> getQuestionsFromIds(List<Long> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Long questionId : questionIds) {
            questions.add(questionRepo.findById(questionId).get());
        }

        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper(
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );
            questionWrappers.add(wrapper);
        }
        return questionWrappers;
    }

    public Integer getScore(List<SubmitRequest> submitRequests) {
        Integer score = 0;
        for (SubmitRequest submitRequest : submitRequests) {
            Question question = questionRepo.findById(submitRequest.getQId()).get();
            if (submitRequest.getAnswer().equals(question.getRightAnswer()))
                score++;
        }
        return score;
    }
}
