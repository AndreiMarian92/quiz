package com.marian.quizz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean is_correct;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("answers")
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonBackReference
    private Questions question;

    @Column
    private Date created_at = new Date();

    public Answers() {
    }

//    public boolean getIsCorrect() {
//        return is_correct;
//    }
//
//    public void setIsCorrect(boolean is_correct) {
//        this.is_correct = is_correct;
//    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", is_correct=" + is_correct +
                ", created_at=" + created_at +
                '}';
    }
    }
