package com.example.assessment.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "author")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

//    @OneToMany
//    @JoinColumn(name = "book_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Book> books;
}

