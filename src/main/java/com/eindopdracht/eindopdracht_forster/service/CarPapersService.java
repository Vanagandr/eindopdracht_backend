package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.exception.CarPaperException;
import com.eindopdracht.eindopdracht_forster.model.CarPapers;
import com.eindopdracht.eindopdracht_forster.repository.CarPapersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CarPapersService {

    private final CarPapersRepository carPapersRepository;

    public CarPapersService(CarPapersRepository carPapersRepository) {
        this.carPapersRepository = carPapersRepository;
    }

    //Stores the car papers
    public CarPapers storeFile(MultipartFile file, String url) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        CarPapers carPapers = new CarPapers(fileName, contentType, url, bytes);

        return carPapersRepository.save(carPapers);
    }

}
