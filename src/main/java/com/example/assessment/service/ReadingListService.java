package com.example.assessment.service;

import com.example.assessment.dto.readinglist.CreateReadingListDTO;
import com.example.assessment.dto.readinglist.GetReadingListDTO;
import com.example.assessment.dto.user.GetUserDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.ReadingList;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.ReadingListRepository;
import com.example.assessment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReadingListService {

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public List<GetReadingListDTO> getAll() {
        return readingListRepository.findAll()
                .stream()
                .map(readingList -> mapper.map(readingList, GetReadingListDTO.class))
                .toList();
    }

    public CreateReadingListDTO create(CreateReadingListDTO createReadingListDTO) {
        try {
            ReadingList readingList = mapper.map(createReadingListDTO, ReadingList.class);
            return mapper.map(readingListRepository.save(readingList), CreateReadingListDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("something went wrong when creating a reading list");
        }
        return null;
    }

    public GetUserDTO getById(Long id) {
        return userRepository
                .findById(id)
                .map(user -> mapper.map(user, GetUserDTO.class))
                .orElseThrow(() -> new NotFoundException("Not found"));
    }

    public GetReadingListDTO getByIdAndListId(Long list_id) {
        return bookRepository
                .findById(list_id)
                .map(readingList -> mapper.map(readingList, GetReadingListDTO.class))
                .orElseThrow(() -> new NotFoundException("Reading list not found"));
    }
}
