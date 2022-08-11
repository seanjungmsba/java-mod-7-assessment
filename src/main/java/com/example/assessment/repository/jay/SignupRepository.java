package com.example.assessment.repository.jay;

import com.example.assessment.model.jay.Signup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignupRepository extends JpaRepository<Signup, Long> {
    List<Signup> findAllByCamperId(Long camperId);
}
