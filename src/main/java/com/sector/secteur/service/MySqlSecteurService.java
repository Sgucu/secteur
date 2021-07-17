package com.sector.secteur.service;

import com.sector.secteur.model.ora.SecteurO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MySqlSecteurService {

    SecteurO store(MultipartFile file, String destination) throws IOException;
    SecteurO store2(MultipartFile file, String destination) throws IOException;
    String getQuery(MultipartFile file, String destination) throws IOException;

}