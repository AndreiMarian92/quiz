package com.marian.quizz.repository;

import com.marian.quizz.model.Questions;
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
            "   qc.questions.id AS idQuestion " +
//            "   qh.created_date AS createdDate " +
//            "   qh.created_by AS createdBy " +
            "FROM QuizzContent AS qc " +
            "JOIN QuizzHeader AS qh " +
            "ON qh.id = qc.quizzHeader.id " +
            "WHERE " +
            "   qh.createdDate > DATEADD(DAY, :days, GETDATE()) " +
            "   AND qh.createdBy = :userId ")
    List<Integer> getAllQuizzQuestionIdsByCreatedByAndPeriod(@Param("userId") int userId, @Param("days") int daysToCheck);


//    Questions findQuestionsByQuizzIdAndPosition(Integer idQuizz, Integer questionPosition);
}
