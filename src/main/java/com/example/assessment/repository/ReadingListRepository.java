package com.example.assessment.repository;

import com.example.assessment.model.ReadingList;
import com.example.assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    ReadingList findByNameAndUser(String name, User user);
    List<ReadingList> findByUser(User user);
}
