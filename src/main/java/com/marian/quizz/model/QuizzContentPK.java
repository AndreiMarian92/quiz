package com.marian.quizz.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class QuizzContentPK implements Serializable {

    @JsonBackReference
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quizz")
    private QuizzHeader quizzHeader;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question")
    private Questions questions;

//    @Column(name = "id_quizz")
//    private Integer idQuizz;
//
//    @Column(name = "id_question")
//    private Integer idQuestion;

}
