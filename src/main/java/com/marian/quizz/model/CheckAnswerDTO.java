package com.marian.quizz.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckAnswerDTO implements Serializable {

    private boolean answerIsCorrect;
    private boolean quizFinalized;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private String finalMessage;


    public CheckAnswerDTO(Boolean answerIsCorrect, QuizzHeader qh, boolean quizFinalized, String finalMessage) {
        this.answerIsCorrect = answerIsCorrect;
        this.quizFinalized = quizFinalized;
        this.totalQuestions = qh.getTotalQuestions();
        this.correctAnswers = qh.getCorrectAnswers();
        this.incorrectAnswers = qh.getIncorrectAnswers();
        this.finalMessage = finalMessage;
    }
//todo: aici nu ar trebui de fapt trimis un obiect de tipul quizzHeader
    //  pentru a oferi informatiile necesare frontendului pentru a updata: intrebarile corecte / incorecte

}
