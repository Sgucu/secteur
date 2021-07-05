package com.sector.secteur.service;

import com.sector.secteur.model.pos.SecteurP;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface PosSecteurService {

    SecteurP store(MultipartFile file, String destination) throws IOException;
  //  SecteurP storeSecond(MultipartFile file) throws IOException;
    SecteurP getFile(Long id);
    Stream<SecteurP> getAllFiles();
}
