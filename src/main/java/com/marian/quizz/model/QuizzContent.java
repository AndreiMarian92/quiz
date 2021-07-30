package com.marian.quizz.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "quizz_content")
@Getter
@Setter
public class QuizzContent {

    @EmbeddedId
    @JsonIgnore
    private QuizzContentPK pk;

    @Column(nullable = false)
    private Integer questionPosition;

    @Column(name = "is_correct")
    private boolean isCorrect;

    public QuizzContent() {
    }

    public QuizzContent(QuizzContentPK pk, Integer questionPosition, boolean isCorrect) {
        this.pk = pk;
        this.questionPosition = questionPosition;
        this.isCorrect = isCorrect;
    }

    public QuizzContent(QuizzHeader quizzHeader, Questions questions, Integer questionPosition){
        pk = new QuizzContentPK();
        pk.setQuizzHeader(quizzHeader);
        pk.setQuestions(questions);
        this.questionPosition = questionPosition;
    }

    @Transient
    public Questions getQuestion(){
        return this.pk.getQuestions();
    }

    @Transient
    public Integer getQuestionPosition(){
        return getQuestionPosition();
    }


//    @Column(name = "id_quizz")
//    private Integer idQuizz;
//
//    @Column(name = "id_question")
//    private Integer idQuestion;



//    @Override
//    public String toString() {
//        return "QuizzContent{" +
//                "id=" + id +
//                ", idQuizz=" + idQuizz +
//                ", idQuestion=" + idQuestion +
//                ", isCorrect=" + isCorrect +
//                '}';
//    }
}


