package com.marian.quizz.repository;

import com.marian.quizz.model.QuizzContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizzContentRepository extends JpaRepository<QuizzContent, Integer> {

    @Query("" +
            "SELECT DISTINCT " +
            "   qc.pk.questions.id as idQuestion " +
            "FROM QuizzContent as qc " +
            "WHERE " +
            "   qc.pk.quizzHeader.createdDate > DATEADD(DAY, :days, GETDATE()) " +
            "   AND qc.pk.quizzHeader.createdBy = :userId ")
    List<Integer> getAllQuizzQuestionIdsByCreatedByAndPeriod(@Param("userId") int userId, @Param("days") int daysToCheck);



}
