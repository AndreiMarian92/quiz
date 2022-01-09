package com.marian.quizz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quizz_content")
@Getter
@Setter
public class QuizzContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer questionPosition;

    @Column(name = "is_correct")
    private Boolean isCorrect;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quizz", referencedColumnName = "id")
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private QuizzHeader quizzHeader;

    @OneToOne(
            optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Questions questions;

    public QuizzContent() {
    }

    public QuizzContent(Integer questionPosition, boolean isCorrect, Questions questions) {
        this.questionPosition = questionPosition;
        this.isCorrect = isCorrect;
        this.questions = questions;
    }

    public QuizzContent(Questions questions) {
        this.questions = questions;
    }

    public void setQuizzHeader(QuizzHeader quizzHeader) {
        this.quizzHeader = quizzHeader;
    }

    @Override
    public String toString() {
        return "QuizzContent{" +
                "id=" + id +
                ", questionPosition=" + questionPosition +
                ", isCorrect=" + isCorrect +
                ", questions=" + questions +
                '}';
    }


}


