package com.marian.quizz.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Questions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @Column
    private Date created_at;

    @Column(name = "image_URL")
    private String imageUrl;

    @Transient
    private Integer questionPosition;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Answers> answers;


    public Questions() {

    }

    public Questions(Integer id) {
        this.id = id;
    }

    public void addAnswers(Answers a) {
        answers.add(a);
        a.setQuestion(this);
    }

    public void deleteAnswers(Answers a) {
        answers.remove(a);
        a.setQuestion(null);
    }


//    @Override
//    public String toString() {
//        return "Questions{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", created_at=" + created_at +
//                ", answers=" + answers +
//                '}';
//    }

}
