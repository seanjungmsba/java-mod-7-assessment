package com.example.assessment.controller.jay;

import com.example.assessment.dto.jay.CamperResponseDTO;
import com.example.assessment.dto.jay.CamperSearchResultDTO;
import com.example.assessment.dto.jay.CreateCamperDTO;
import com.example.assessment.service.jay.CamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campers")
public class CamperController {

    @Autowired
    private CamperService camperService;

    @PostMapping
    public CamperSearchResultDTO createCamper(@Valid @RequestBody CreateCamperDTO createCamperDTO) {
        return camperService.create(createCamperDTO);
    }

    @GetMapping
    public List<CamperSearchResultDTO> getAllCampers() {
        return camperService.getAll();
    }

    @GetMapping("/{id}")
    public CamperResponseDTO getCamper(@PathVariable Long id) {
        return camperService.getById(id);
    }
}
