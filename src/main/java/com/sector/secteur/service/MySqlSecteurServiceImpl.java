package com.sector.secteur.service;

import com.sector.secteur.model.ora.SecteurO;
import com.sector.secteur.model.pos.SecteurP;
import com.sector.secteur.repository.ora.MySqlFileDBRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MySqlSecteurServiceImpl implements MySqlSecteurService {


    private static String QUERY = "";


    @Autowired
    @Qualifier("mySqlFileDBRepository")
    public MySqlFileDBRepository mySqlFileDBRepository;

    SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss z");
    Date sysdate = new Date(System.currentTimeMillis());

    @Override
    public SecteurO store(MultipartFile file, String destination) throws IOException {


        String date = dFormat.format(sysdate);

        String comment = "This is a test comment";


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        String content = IOUtils.toString(stream,StandardCharsets.US_ASCII);

        // System.out.println(content);
        SecteurO secteurO = new SecteurO(destination,fileName, file.getContentType(), file.getBytes(), comment, date);
        return mySqlFileDBRepository.save(secteurO);

    }

    @Override
    public SecteurO store2(MultipartFile file, String destination) throws IOException {

        String date = dFormat.format(sysdate);
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

        return mySqlFileDBRepository.save(secteurO);
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
