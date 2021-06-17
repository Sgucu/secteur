package com.sector.secteur.model;

import javax.persistence.*;

@Entity
@Table(name = "secteur_application_data")
public class Secteur {


    @Id
    private Long id;
    private String type;
    private String name;

    @Lob
    private byte[] attachmentData;

    private String comment;
    private String date;

    public Secteur(){

    }

    public Secteur( String name, String type, byte[] attachmentData, String comment, String date) {

        this.name = name;
        this.type = type;
        this.attachmentData = attachmentData;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(byte[] attachmentData) {
        this.attachmentData = attachmentData;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
