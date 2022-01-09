package com.marian.quizz.controller;


import com.marian.quizz.model.CheckAnswerDTO;
import com.marian.quizz.model.QuestionDTO;
import com.marian.quizz.model.Questions;
import com.marian.quizz.model.QuizzHeader;
import com.marian.quizz.service.QuizzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    Logger logger = LoggerFactory.getLogger(QuizzController.class);

    @PostMapping("/quizz/generate")
    public Integer generate(){

        logger.info("Started generating a new quiz.");
        Integer quizzNumber = quizzService.generateQuizz();
        logger.info("Finished generating a new quiz with id: {}.", quizzNumber);
        return quizzNumber;
    }

    @GetMapping("/quizz/{id}")
    public QuizzHeader getQuizzById(@PathVariable Integer id){
        return quizzService.getQuizzById(id);
    }

    @GetMapping("/quizz/{id}/{pos}")
    public QuestionDTO getQuizzQuestionByParams(@PathVariable Integer id, @PathVariable Integer pos){
        return quizzService.getQuestionIdByParams(id, pos);
    }


    @PostMapping("/quizz/check")
    public CheckAnswerDTO checkQuestion(@RequestBody QuestionDTO questionDTO){
        return quizzService.checkQuestion(questionDTO);
    }

}
