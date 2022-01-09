package com.marian.quizz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswersDTO implements Serializable {

    private Integer id;

    private Boolean is_correct;

    private String description;

    public AnswersDTO(Answers answer) {
        this.id = answer.getId();
        this.is_correct = answer.is_correct();
        this.description = answer.getDescription();
    }
}
