package com.sector.secteur.service;

import com.sector.secteur.model.SecteurP;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface SecteurService {

    SecteurP store(MultipartFile file) throws IOException;
    SecteurP storeSecond(MultipartFile file) throws IOException;
    SecteurP getFile(Long id);
    Stream<SecteurP> getAllFiles();
}
