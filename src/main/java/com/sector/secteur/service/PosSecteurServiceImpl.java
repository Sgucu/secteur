package com.sector.secteur.service;



import com.sector.secteur.model.pos.SecteurP;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import com.sector.secteur.repository.pos.PosFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        InputStream inputStream = file.getInputStream();



        try{
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);

            //String groupPattern = "(CREATE TABLE(?s:.)*\\n\\);)";
            String groupPattern = "CREATE TABLE.*?;";
            Pattern pattern = Pattern.compile(groupPattern);
            Matcher matcher = pattern.matcher(data);

            System.out.println(matcher.matches());

            System.out.println(matcher.group(0));

        }catch (IOException e){
          e.printStackTrace();
        }

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
