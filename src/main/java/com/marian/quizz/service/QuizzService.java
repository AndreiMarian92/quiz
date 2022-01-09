package com.marian.quizz.service;


import com.marian.quizz.model.*;
import com.marian.quizz.repository.QuestionsRepository;
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

    private static final int days = -1;



    @Autowired
    private QuizzHeaderRepository quizzHeaderRepository;

    @Autowired
    private QuizzContentRepository quizzContentRepository;


    public Integer generateQuizz() {
        List<Integer> allDbQuestionIds = questionsRepository.getAllIds();
        logger.info("Found {} question ids in database.", allDbQuestionIds.size());

        List<Integer> ignoredQuestionIds = quizzContentRepository.getAllQuizzQuestionIdsByCreatedByAndPeriod(MockupUsers.MARIAN.getUserId(), days);

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
        quizzHeader.setCreatedDate(new Date());
        quizzHeader.setEndDate(addMinutesToJavaUtilDate(quizzHeader.getCreatedDate(), 30));
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


    //Metoda verificare intrebare
    public CheckAnswerDTO checkQuestion(QuestionDTO questionDTO){
//        QuestionDTO answeredQuestion = questionDTO;
        CheckAnswerDTO response = null;
        Optional<Questions> questionFromDatabase = questionsRepository.findById(questionDTO.getQuestionId());

        QuestionDTO dbQuestion = questionMapToDTO(questionFromDatabase);

        dbQuestion.setQuizzId(questionDTO.getQuizzId());

        Map<Integer, Boolean> dbQuestionMap = dbQuestion.getAnswersDTO().stream().collect(Collectors.toMap(AnswersDTO::getId, AnswersDTO::getIs_correct));


//        Boolean result = compareResultsOption1(questionDTO, dbQuestionMap);
        Boolean answerIsCorrect = compareResultsOption2(questionDTO, dbQuestionMap);


        QuizzContent dbQuizzContent = quizzContentRepository.findOneByQuizzHeader_idAndQuestions_id(questionDTO.getQuizzId(), questionDTO.getQuestionId());
        dbQuizzContent.setIsCorrect(answerIsCorrect);
        quizzContentRepository.save(dbQuizzContent);

        Optional<QuizzHeader> quizzHeader = quizzHeaderRepository.findById(questionDTO.getQuizzId());


        if(quizzHeader.isPresent()){
            QuizzHeader qh = quizzHeader.get(); // ?? ar trebui sa aduca tot obiectul insa nu aduce nimic?
            Date deadline = addMinutesToJavaUtilDate(qh.getCreatedDate(), 30);
            Date currentDate = new Date();

            Integer correctAnswers = qh.getCorrectAnswers();
            Integer incorrectAnswers = qh.getIncorrectAnswers();

            if(answerIsCorrect) {
                ++correctAnswers;
                qh.setCorrectAnswers(correctAnswers);
            } else {
                ++incorrectAnswers;
                qh.setIncorrectAnswers(incorrectAnswers);
            }

            boolean quizFinalized = false;
            String finalMessage = "";
            if(incorrectAnswers == 5 || currentDate.after(deadline)){
                quizFinalized = true;
                qh.setIsPassed(false);
                finalMessage = "We are sorry, but you failed this quiz...";
            } else if (correctAnswers >= 22 && correctAnswers + incorrectAnswers == 26) {
                quizFinalized = true;
                qh.setIsPassed(true);
                finalMessage = "Well done!";
            }
            QuizzHeader qhDb = quizzHeaderRepository.save(qh);

            response = new CheckAnswerDTO(answerIsCorrect, qhDb, quizFinalized, finalMessage);
        }



        return response;
    }

    public Date addMinutesToJavaUtilDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    private Boolean compareResultsOption2(QuestionDTO questionDTO, Map<Integer, Boolean> dbQuestionMap) {
        return questionDTO.getAnswersDTO().stream()
                .filter(f -> !f.getIs_correct().equals(dbQuestionMap.get(f.getId())))
                .findFirst()
                .map(m -> false)
                .orElse(true);
    }

    private boolean compareResultsOption1(QuestionDTO questionDTO, Map<Integer, Boolean> dbQuestionMap) {
        List<Boolean> results = new ArrayList<>();
        questionDTO.getAnswersDTO().forEach(e -> {
            Boolean result = e.getIs_correct().equals(
                    dbQuestionMap.get(e.getId())
            );

            results.add(result);
        });

        return !results.contains(false);
    }

    //metoda mapare entitate pe DTO
    public QuestionDTO questionMapToDTO(Optional<Questions> questions){
        QuestionDTO mappedQuestionDTO = new QuestionDTO();
        mappedQuestionDTO.setQuestionId(questions.get().getId());
        mappedQuestionDTO.setDescription(questions.get().getDescription());

        Set<AnswersDTO> answersDTOSet = new HashSet<>();

        questions.get().getAnswers().forEach(e -> {
            AnswersDTO answer = new AnswersDTO(e);
            answersDTOSet.add(answer);
        });

        mappedQuestionDTO.setAnswersDTO(answersDTOSet);

        return mappedQuestionDTO;
    }



    // todo: daca la o intrebare a fost dat raspunsul, sa nu mai revina la ea >> se poate verifica in functie de
    //       parametrul is_correct care default este NULL. Daca are o valoare, intrebarea sa fie sarita, sa nu mai revina la ea

    //todo:
    //  daca numarul intrebarilor gresite > 5, atunci quizz-ul este invalidat (failed) >> done
    //  calculare la fiecare raspuns numarul de intrebari gresite ? >> done
    //


    //todo:
    //  metoda validare quizz
    //  daca numarul de intrebari corecte => 22, quizz-ul este considerat reusit
    //  dar acesta nu se termina pana in momentul in care s-a raspuns la toate intrebarile.
    //  deci calcularea intrebarilor corecte trebuie sa fie la finalul quizz-ului, pentru a evita terminarea "prematura"

    //todo:
    //



    //metoda aducere intrebare in functie de id chestionar si pozitia intrebarii
    public QuestionDTO getQuestionIdByParams(Integer quizzId, Integer questionPosition){
        QuizzContent quizzContent = quizzContentRepository.findFirstByQuizzHeader_idAndQuestionPositionGreaterThanEqualAndIsCorrectIsNullOrderByQuestionPositionAsc(quizzId, questionPosition);

        Questions questions = quizzContent.getQuestions();

        questions.setQuestionPosition(quizzContent.getQuestionPosition());


        QuestionDTO questionDTO = new QuestionDTO(questions);
        questionDTO.setQuizEndDate(quizzContent.getQuizzHeader().getEndDate());



       return questionDTO;
    }
}
