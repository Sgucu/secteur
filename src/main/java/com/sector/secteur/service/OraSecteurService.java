package com.sector.secteur.service;

import com.sector.secteur.model.ora.SecteurO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OraSecteurService {

    SecteurO store(MultipartFile file, String destination) throws IOException;
    String getQuery(MultipartFile file, String destination) throws IOException;
    SecteurO getFile(Long id);
    void executeQuery();

}