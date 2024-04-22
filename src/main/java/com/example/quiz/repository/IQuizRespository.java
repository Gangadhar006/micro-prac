package com.example.quiz.repository;

import com.example.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizRespository extends JpaRepository<Quiz, Long> {
}
