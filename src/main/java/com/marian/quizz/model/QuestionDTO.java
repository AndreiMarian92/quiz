package com.marian.quizz.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDTO implements Serializable {

    private Integer quizzId;

    private Integer questionId;

    private String description;

    private Integer questionPosition;

    private Date quizEndDate;

    private String imageUrl;

    private Set<AnswersDTO> answersDTO;


    public QuestionDTO(Questions questions) {
        this.questionId = questions.getId();
        this.description = questions.getDescription();
        this.questionPosition = questions.getQuestionPosition();
        this.imageUrl = questions.getImageUrl();
        this.answersDTO = questions.getAnswers().stream()
                .map(AnswersDTO::new)
                .collect(Collectors.toSet());
    }

}



