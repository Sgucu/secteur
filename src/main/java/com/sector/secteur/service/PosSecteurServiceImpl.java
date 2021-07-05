package com.sector.secteur.service;



import com.sector.secteur.model.pos.SecteurP;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.sector.secteur.repository.pos.PosFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PosSecteurServiceImpl implements PosSecteurService {


    @Autowired
    private PosFileDBRepository posFileDBRepository;




    @Override
    public SecteurP store(MultipartFile file, String destination) throws IOException {

        String date = "17/06/2021";


        String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        SecteurP secteurP = new SecteurP(destination,fileName, file.getContentType(), file.getBytes(), comment, date);

        return posFileDBRepository.save(secteurP);

    }
/*
    @Override
    public SecteurP storeSecond(MultipartFile file) throws IOException {

        String date = "17/06/2021";


        String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String content = IOUtils.toString(stream,StandardCharsets.US_ASCII);

       // System.out.println(content);
        SecteurP secteurP = new SecteurP(fileName, content,  file.getBytes(), comment, date);
        return posFileDBRepository.save(secteurP);

    }*/


    @Override
    public SecteurP getFile(Long id) {
        return posFileDBRepository.findById(id).get();
    }

    @Override
    public Stream<SecteurP> getAllFiles() {
        return posFileDBRepository.findAll().stream();
    }
}
