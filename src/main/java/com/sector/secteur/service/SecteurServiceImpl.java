package com.sector.secteur.service;



import com.sector.secteur.model.SecteurP;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.sector.secteur.reposytory.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Service
public class SecteurServiceImpl implements  SecteurService {


    @Autowired
    private FileDBRepository fileDBRepository;




    @Override
    public SecteurP store(MultipartFile file) throws IOException {

        String date = "17/06/2021";


       String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        SecteurP secteurP = new SecteurP(fileName, file.getContentType(), file.getBytes(), comment, date);

        return fileDBRepository.save(secteurP);

    }

    @Override
    public SecteurP storeSecond(MultipartFile file) throws IOException {

        String date = "17/06/2021";


        String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String content = IOUtils.toString(stream,StandardCharsets.US_ASCII);

       // System.out.println(content);

        SecteurP secteurP = new SecteurP(fileName, content,  file.getBytes(), comment, date);



        return fileDBRepository.save(secteurP);

    }


    @Override
    public SecteurP getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<SecteurP> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
