package com.sector.secteur.service;

import com.sector.secteur.model.ora.SecteurO;
import com.sector.secteur.repository.ora.OraFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class OraSecteurServiceImpl implements OraSecteurService{


    @Autowired
    public OraFileDBRepository oraFileDBRepository;

    @Override
    public SecteurO store(MultipartFile file, String destination) throws IOException {

        String date = "17/06/2021";


        String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        SecteurO secteurO = new SecteurO(destination,fileName, file.getContentType(), file.getBytes(), comment, date);

        return oraFileDBRepository.save(secteurO);
    }

    @Override
    public SecteurO getFile(Long id) {
        return null;
    }
}
