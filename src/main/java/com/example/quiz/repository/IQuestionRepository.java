package com.example.quiz.repository;

import com.example.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> fetchRandomQuestionsByCategory(String category, Long numQ);
}