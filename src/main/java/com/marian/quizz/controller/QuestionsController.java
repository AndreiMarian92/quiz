package com.marian.quizz.controller;

import com.marian.quizz.model.Answers;
import com.marian.quizz.repository.QuestionsRepository;
import com.marian.quizz.model.Questions;
import com.marian.quizz.service.QuestionsService;
import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @GetMapping("/questions/all")
    public List<Questions> get(){
        return questionsService.getAllQuestions();
    }

    @PostMapping("/questions/add")
    public Questions save(@RequestBody Questions questions){

        for (Answers answers: questions.getAnswers()){
            answers.setQuestion(questions);
        }

        questions.setAnswers(questions.getAnswers());

        return questionsService.addOrUpdate(questions);
    }

    @GetMapping("/questions/{id}")
    public List<Questions> getOneQuestion(@PathVariable Integer id){
        Optional<Questions> questionById = Optional.ofNullable(questionsService.getQuestionById(id));
        if(questionById.isPresent()){
            return Arrays.asList(questionById.get());
        }
        else {
            throw new RuntimeException("Intrebarea nu exista pentru id-ul " + id);
        }
    }


//    public Questions get(@PathVariable int id){
//        Optional<Questions> questions = questionsRepository.findById(id);
//}

    @PutMapping("/questions/update")
    public Questions update(@RequestBody Questions questions){
        questions.getAnswers().forEach(a -> a.setQuestion(questions));
        return questionsService.addOrUpdate(questions);
    }

    @DeleteMapping("/questions/{id}")
    public void delete(@PathVariable int id){
        Optional<Questions> questions = Optional.ofNullable(questionsService.getQuestionById(id));
        if (questions.isPresent()){
            questionsService.deleteById(questions.get().getId());
        }
        else {
            throw new RuntimeException("Intrebarea cu id-ul " + id + " nu a fost gasita!");
        }
    }
}
