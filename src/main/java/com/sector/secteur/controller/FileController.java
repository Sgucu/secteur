package com.sector.secteur.controller;

import com.sector.secteur.message.ResponseFile;
import com.sector.secteur.message.ResponseMessage;
import com.sector.secteur.model.Secteur;
import com.sector.secteur.reposytory.FileDBRepository;
import com.sector.secteur.service.SecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class FileController {

    @Autowired
    private SecteurService secteurService;


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file){

        String message = "";
        try{
            secteurService.store(file);

            message = "Uploaded the file successfully : " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));


        }catch (IOException e){

            message = "Could not upload the file: " + file.getOriginalFilename()+ "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


   /*
   @GetMapping("/files")
   public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = secteurService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(), fileDownloadUri,dbFile.getType(),dbFile.getDate().length());
        });



        return ResponseEntity.status(HttpStatus.OK).body(files);

    }*/


    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id){

        Secteur fileDB = secteurService.getFile(id);

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileDB.getName() + "\"")
                .body(fileDB.getAttachmentData());

    }



}
