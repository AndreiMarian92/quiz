package com.marian.quizz.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MockupUsers {
    MARIAN(1);

    private int userId;


    MockupUsers(int userId) {
        this.userId = userId;
    }

}
