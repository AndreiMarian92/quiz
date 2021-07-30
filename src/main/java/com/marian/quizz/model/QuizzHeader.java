package com.marian.quizz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizz_header")
@Getter
public class QuizzHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "is_passed")
    private boolean isPassed;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.quizzHeader")
    private List<QuizzContent> quizzContent = new ArrayList<>();

//    @ManyToMany(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "quizz_content",
//            joinColumns = @JoinColumn(name = "id_question", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "id_quizz", referencedColumnName = "id")
//    )
//    Set<Questions> questions;




    public QuizzHeader(Integer createdBy, Integer totalQuestions) {
        this.createdBy = createdBy;
        this.totalQuestions = totalQuestions;
    }

    public QuizzHeader() {
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
}
