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
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadingList {
    @Id
    private Long id;
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "readingLists") // many-to-many relationship between ReadingList and Book
    private List<Book> books;

    @ManyToOne // many-to-one relationship between ReadingList and User
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
