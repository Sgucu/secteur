package com.sector.secteur.service;

import com.sector.secteur.model.ora.SecteurO;
import com.sector.secteur.repository.ora.OraFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OraSecteurServiceImpl implements OraSecteurService{


    private static String QUERY = "";


    @Autowired
    @Qualifier("oraFileDBRepository")
    public OraFileDBRepository oraFileDBRepository;

    @Override
    public SecteurO store(MultipartFile file, String destination) throws IOException {

        String date = "17/07/2021";
        String comment = "This is a test comment";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        InputStream inputStream = file.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream,StandardCharsets.UTF_8);

        //System.out.println(inputStream);

        try{

            BufferedReader br = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String st;
            while ((st = br.readLine())!=null){
                stringBuilder.append(st);
            }

            //String groupPattern = "(CREATE TABLE(?s:.)*\\n\\);)";
            String groupPattern = "CREATE TABLE.*?;";

            Pattern pattern = Pattern.compile(groupPattern);
            Matcher matcher = pattern.matcher(stringBuilder);

            System.out.println(matcher.find());
            System.out.println(matcher.group(0));

        }catch (IllegalStateException e){
            e.printStackTrace();
        }

        SecteurO secteurO = new SecteurO(destination,fileName, file.getContentType(), file.getBytes(), comment, date);

        return oraFileDBRepository.save(secteurO);
    }

    @Override
    public String getQuery(MultipartFile file, String destination) throws IOException {

        InputStream inputStream = file.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream,StandardCharsets.UTF_8);

        try{

            BufferedReader br = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String st;
            while ((st = br.readLine())!=null){
                stringBuilder.append(st);
            }


            String groupPattern = "CREATE TABLE.*?;";

            Pattern pattern = Pattern.compile(groupPattern);
            Matcher matcher = pattern.matcher(stringBuilder);


            if(matcher.find()){
                QUERY = matcher.group(0);
            }
        //    System.out.println(matcher.group(0));


           return QUERY ;

        }catch (IllegalStateException e){
            e.printStackTrace();
        }

        return "No query found";
    }


}
