package com.marian.quizz.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private Date created_at = new Date();

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Answers> answers;


//    @ManyToMany(mappedBy = "questions")
//    @JsonIgnoreProperties("questions")
//    Set<QuizzHeader> quizzHeaderSet;


    public Questions() {

    }

    public Questions(Integer id) {
        this.id = id;
    }

    public void addAnswers(Answers a) {
//        Questions questions = new Questions();
        answers.add(a);
        a.setQuestion(this);
    }

    public void deleteAnswers(Answers a) {
        answers.remove(a);
        a.setQuestion(null);
    }


    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", answers=" + answers +
                '}';
    }


}
