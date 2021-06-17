package com.sector.secteur.service;

import com.sector.secteur.model.Secteur;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface SecteurService {

    Secteur store(MultipartFile file) throws IOException;
    Secteur getFile(Long id);
    Stream<Secteur> getAllFiles();
}
