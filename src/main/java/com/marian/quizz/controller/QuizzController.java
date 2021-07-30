package com.marian.quizz.controller;


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

}
