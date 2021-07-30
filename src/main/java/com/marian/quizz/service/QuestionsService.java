package com.marian.quizz.service;

import com.marian.quizz.model.Answers;
import com.marian.quizz.model.Questions;
import com.marian.quizz.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

@Service
public class QuestionsService {
    @Autowired
    QuestionsRepository questionsRepository;

    public Questions addOrUpdate(Questions question){
        return questionsRepository.save(question);
    }


    public String deleteById(int id){
        questionsRepository.deleteById(id);
        return "Intrebarea cu id-ul " + id + " a fost stearsa cu succes!";
    }

    public List<Questions> getAllQuestions(){
        return questionsRepository.findAll();
    }

    public Questions getQuestionById(int id){
        return questionsRepository.findById(id).get();
    }
}
