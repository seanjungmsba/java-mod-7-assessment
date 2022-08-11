package com.example.assessment.service.jay;

import com.example.assessment.dto.jay.ActivityDTO;
import com.example.assessment.dto.jay.CreateSignUpDTO;
import com.example.assessment.exception.ValidationException;
import com.example.assessment.model.jay.Signup;
import com.example.assessment.repository.jay.SignupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignupService {
    @Autowired
    private SignupRepository repository;

    @Autowired
    private CamperService camperService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ModelMapper modelMapper;

    public ActivityDTO create(CreateSignUpDTO createSignUpDTO) {
        Signup signup = new Signup();
        signup.setTime(createSignUpDTO.getTime());
        signup.setCamper(camperService.getCamperById(createSignUpDTO.getCamperId()).orElseThrow(() -> new ValidationException()));
        signup.setActivity(activityService.getActivityById(createSignUpDTO.getActivityId()).orElseThrow(() -> new ValidationException()));
        signup = repository.save(signup);
        return modelMapper.map(signup.getActivity(), ActivityDTO.class);
    }

    public List<ActivityDTO> getActivitiesByCamperId(Long camperId) {
        List<Signup> signupsForCamper = repository.findAllByCamperId(camperId);
        return signupsForCamper.stream()
                .map(signup -> signup.getActivity())
                .map(activity -> modelMapper.map(activity,
                    ActivityDTO.class))
                .toList();
    }
}
