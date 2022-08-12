package com.example.assessment.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @Min(value = 0)
    @Max(Integer.MAX_VALUE)
    private int pages;

    @Temporal(TemporalType.DATE)
    private Date published;

    @ManyToMany(mappedBy = "bookSet") // many-to-many relationship between Book and ReadingList
    private Set<ReadingList> readingSet = new HashSet<ReadingList>();

    @ManyToMany(mappedBy = "bookSet") // many-to-many relationship between Book and Genre
    private Set<Genre> genreSet = new HashSet<Genre>();

    @ManyToOne // many-to-one relationship between Book and Author
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;

}
