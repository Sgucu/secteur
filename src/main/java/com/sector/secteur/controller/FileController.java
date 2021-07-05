package com.sector.secteur.controller;

import com.sector.secteur.message.ResponseMessage;
import com.sector.secteur.model.pos.SecteurP;
import com.sector.secteur.service.OraSecteurService;
import com.sector.secteur.service.PosSecteurService;
import com.sector.secteur.service.PosSecteurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class FileController {

    @Autowired
    private PosSecteurService posSecteurService;


    @Autowired
    private OraSecteurService oraSecteurService;





    @PostMapping("/upload/{destination}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String destination, @RequestParam("file")MultipartFile file){

        String message = "";
        try{

            if(destination.equals("Oracle")){

                oraSecteurService.store(file,destination);

                message = "Uploaded the file successfully : " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

            }else if (destination.equals("Postgres")){

                posSecteurService.store(file,destination);

                message = "Uploaded the file successfully : " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

            }else {
                message = "Could not upload the file: " + file.getOriginalFilename()+ "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }




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

/*
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id){

        SecteurP fileDB = posSecteurService.getFile(id);

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileDB.getName() + "\"")
                .body(fileDB.getAttachmentData());

    }
*/


}
