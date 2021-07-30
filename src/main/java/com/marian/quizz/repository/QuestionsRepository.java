package com.marian.quizz.repository;

import com.marian.quizz.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

    void deleteById(Questions questions);

    @Query("select id from Questions")
    List<Integer> getAllIds();






}
