package com.example.assessment.service.jay;

import com.example.assessment.dto.jay.CamperResponseDTO;
import com.example.assessment.dto.jay.CamperSearchResultDTO;
import com.example.assessment.dto.jay.CreateCamperDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.jay.Camper;
import com.example.assessment.repository.jay.CamperRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamperService {
    @Autowired
    private CamperRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private SignupService signupService;

    public CamperSearchResultDTO create(CreateCamperDTO createCamperDTO) {
        Camper camper = modelMapper.map(createCamperDTO, Camper.class);
        return modelMapper.map(repository.save(camper), CamperSearchResultDTO.class);
    }

    public List<CamperSearchResultDTO> getAll() {
        return repository.findAll().stream().map(camper -> modelMapper.map(camper, CamperSearchResultDTO.class)).toList();
    }

    public CamperResponseDTO getById(Long id) {
        CamperResponseDTO camperResponseDTO = repository
                .findById(id)
                .map(camper -> modelMapper.map(camper,
                CamperResponseDTO.class)).orElseThrow(() -> new NotFoundException("Camper not found"));
        camperResponseDTO.setActivities(signupService.getActivitiesByCamperId(id));
        return camperResponseDTO;
    }

    public Optional<Camper> getCamperById(Long id) {
        return repository.findById(id);
    }

}
