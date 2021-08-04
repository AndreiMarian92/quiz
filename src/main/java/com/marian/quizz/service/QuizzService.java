package com.marian.quizz.service;


import com.marian.quizz.model.*;
import com.marian.quizz.repository.QuestionsRepository;
//import com.marian.quizz.repository.QuizzContentRepository;
import com.marian.quizz.repository.QuizzContentRepository;
import com.marian.quizz.repository.QuizzHeaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class QuizzService {

    @Autowired
    private QuestionsRepository questionsRepository;

    private static final Logger logger = LoggerFactory.getLogger(QuizzService.class);

    private static final int days = -16;



    @Autowired
    private QuizzHeaderRepository quizzHeaderRepository;

    @Autowired
    private QuizzContentRepository quizzContentRepository;


    public Integer generateQuizz() {
        List<Integer> allDbQuestionIds = questionsRepository.getAllIds();
        logger.info("Found {} question ids in database.", allDbQuestionIds.size());

        List<Integer> ignoredQuestionIds = quizzContentRepository.getAllQuizzQuestionIdsByCreatedByAndPeriod(MockupUsers.MARIAN.getUserId(), days);







//        quizzContentRepository.getAllQuizzQuestionIdsByCreatedByAndPeriod(MockupUsers.MARIAN.getUserId(), days);

//                quizzHeaderRepository.getAllQuizzQuestionIdsByCreatedByAndPeriod(MockupUsers.MARIAN.getUserId(), days);
        logger.info("Found {} distinct question ids encountered in the past {} days, for user with id: {}.", ignoredQuestionIds.size(), days, MockupUsers.MARIAN.getUserId());

        List<Integer> differences = allDbQuestionIds.stream()
                .filter(element -> !ignoredQuestionIds.contains(element))
                .collect(Collectors.toList());

        logger.info("The difference between total question ids and ignored question ids is: {}.", differences.size());




        int numberOfQuestions = 26;
        Random rand = new Random();

        List<Integer> finalQuizzQuestionIds = new ArrayList<>();

        for ( int i= 0; i < numberOfQuestions ; i++){
            int randomIndex = rand.nextInt(differences.size());
            finalQuizzQuestionIds.add(differences.get(randomIndex));
            differences.remove(randomIndex);
        }

        Set<Questions> questions = finalQuizzQuestionIds.stream()
                .map(Questions::new)
                .collect(Collectors.toSet());

        AtomicInteger i = new AtomicInteger(1);
        Set<QuizzContent> quizzContents = questions.stream()
                .map(QuizzContent::new)
                .collect(Collectors.toSet());

        quizzContents.forEach(e -> {
            e.setQuestionPosition(i.get());
            i.getAndIncrement();
        });

        QuizzHeader quizzHeader = new QuizzHeader(MockupUsers.MARIAN.getUserId(), quizzContents.size());
        QuizzHeader dbQuizzHeader = quizzHeaderRepository.save(quizzHeader);

        quizzContents.forEach(element -> element.setQuizzHeader(dbQuizzHeader));

        quizzContentRepository.saveAll(quizzContents);



        //todo: metoda pentru setarea pozitiei intrebarii in quizz Content



        return dbQuizzHeader.getId();
    }

    public QuizzHeader getQuizzById(Integer id) {
        QuizzHeader quizzHeader = quizzHeaderRepository.getById(id);


       return quizzHeader ;
    }



    //todo: metoda pentru aducerea intrebarii in functie de id quizz si pozitia intrebarii.


    //todo: metoda pentru setarea parametrului is_correct!
    // se trimite un obiect de tipul quizz content, se aduce intrebarea + raspunsurile pe baza id_content , se compara raspunsul userului cu cel din baza.
    // ulterior se seteaza is_correct in functie de comparatie.
    // daca raspunsurile > 5, metoda pentru anularea quizz-ului. (failed)


    //


    //todo: metoda validare quizz :
    //



    public Questions getQuizzQuestionByParams (Integer idQuizz, Integer questionPosition) {

//       return quizzContentRepository.findQuestionsByQuizzIdAndPosition(idQuizz, questionPosition);

        return null;
    }






//        return  null;
//    }

//    public Questions getQuizzQuestionByParams(Integer idQuizz, Integer questionPosition) throws Exception {
//        Optional<QuizzHeader> newQuizz = quizzHeaderRepository.findById(idQuizz);
//
//        if (newQuizz.isEmpty()) {
//            throw new Exception("Quizz not found for id: " + idQuizz);
//        }
//
//        Questions question = newQuizz.get().getQuestions().stream()
//                .filter(element -> questionPosition.equals(element.))
//
//
//        return null;
//    }
}
