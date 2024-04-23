package com.example.quizservice.repository;

import com.example.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizRespository extends JpaRepository<Quiz, Long> {
}