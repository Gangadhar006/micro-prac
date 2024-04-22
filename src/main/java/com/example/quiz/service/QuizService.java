package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.payload.QuestionWrapper;
import com.example.quiz.payload.SubmitRequest;
import com.example.quiz.repository.IQuestionRepository;
import com.example.quiz.repository.IQuizRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final IQuizRespository quizRepo;
    private final IQuestionRepository questionRepo;

    public List<Question> createQuiz(String category, Long numQ, String title) {
        List<Question> questions = questionRepo.fetchRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return questions;
    }

    public List<QuestionWrapper> fetchQuiz(Long quizId) {
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        List<Question> questions = quizRepo.findById(quizId).get().getQuestions();
        for (Question question : questions) {
            questionWrapperList.add(new QuestionWrapper(
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4())
            );
        }
        return questionWrapperList;
    }

    public Long submitQuiz(List<SubmitRequest> submitRequests, Long quizId) {
        List<Question> questions = quizRepo.findById(quizId).get().getQuestions();
        Long score = 0L;
        for (SubmitRequest request : submitRequests) {
            for (Question question : questions) {
                if (request.getQId().equals(question.getId()))
                    if (request.getAnswer().equals(question.getRightAnswer()))
                        score++;
            }
        }
        return score;
    }
}