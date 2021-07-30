package com.marian.quizz.repository;

import com.marian.quizz.model.QuizzHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzHeaderRepository extends JpaRepository<QuizzHeader, Integer> {

//    @Query("" +
//            "SELECT DISTINCT " +
//            "   qc.idQuestion " +
//            "from QuizzContent as qc " +
//            "INNER JOIN QuizzHeader as qh " +
//            "   on qc.idQuizz = qh.id " +
//            "WHERE " +
//            "   qh.createdDate > DATEADD(DAY, :days, GETDATE()) " +
//            "   AND qh.createdBy = :userId ")
//    List<Integer> getAllQuizzQuestionIdsByCreatedByAndPeriod(@Param("userId") int userId, @Param("days") int daysToCheck);



//    select distinct
//    qc.id_question
//    from quizz_header as qh
//    inner join  quizz_content as qc
//    on	qc.id_quizz = qh.id
//            where
//    qh.created_date > DATEADD(DAY, -62, GETDATE())
//    and qh.created_by = 1
}
