package com.example.assessment.controller.jay;

import com.example.assessment.dto.jay.ActivityDTO;
import com.example.assessment.service.jay.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteActivityById(@PathVariable Long id) {
        activityService.deleteById(id);
    }

}
