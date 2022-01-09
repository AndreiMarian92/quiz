package com.marian.quizz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quizz_header")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuizzHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_passed")
    private Boolean isPassed;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @Column(name = "incorrect_answers")
    private Integer incorrectAnswers;



    @OneToMany(
            mappedBy = "quizzHeader",
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<QuizzContent> quizzContent = new HashSet<>();


    public QuizzHeader(Integer createdBy, Integer totalQuestions) {
        this.createdBy = createdBy;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
    }

    public QuizzHeader(Integer id, Integer createdBy, Date createdDate, boolean isPassed, Integer totalQuestions, Integer correctAnswers, Set<QuizzContent> quizzContent) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.isPassed = isPassed;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.quizzContent = quizzContent;
    }

    public QuizzHeader() {
    }

    public QuizzHeader(int userId, int size, Set<QuizzContent> quizzContents) {
        this.createdBy = userId;
        this.totalQuestions = size;
        this.quizzContent = quizzContents;
    }

    public QuizzHeader(int userId, Set<QuizzContent> quizzContent) {
        this.createdBy = userId;
        this.totalQuestions = quizzContent.size();
        this.quizzContent = quizzContent;
    }


    @Override
    public String toString() {
        return "QuizzHeader{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", isPassed=" + isPassed +
                ", totalQuestions=" + totalQuestions +
                ", correctAnswers=" + correctAnswers +
                ", quizzContent=" + quizzContent +
                '}';
    }

    public void addQuizzContent(QuizzContent q){
        quizzContent.add(q);
        q.setQuizzHeader(this);
    }


}
