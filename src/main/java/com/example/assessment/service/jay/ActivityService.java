package com.example.assessment.service.jay;

import com.example.assessment.dto.jay.ActivityDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.jay.Activity;
import com.example.assessment.repository.jay.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<ActivityDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(activity -> mapper.map(activity, ActivityDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new NotFoundException("Activity not found");
        }
    }

    public Optional<Activity> getActivityById(Long id) {
        return repository.findById(id);
    }
}