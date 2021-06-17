package com.sector.secteur.service;

import org.springframework.util.StringUtils;
import com.sector.secteur.model.Secteur;
import com.sector.secteur.reposytory.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

public class SecteurServiceImpl implements  SecteurService {


    @Autowired
    private FileDBRepository fileDBRepository;


    @Override
    public Secteur store(MultipartFile file) throws IOException {

       String date = "17/06/2021";
       String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Secteur secteur = new Secteur(fileName, file.getContentType(), file.getBytes(), comment, date);

        return fileDBRepository.save(secteur);

    }

    @Override
    public Secteur getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<Secteur> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
