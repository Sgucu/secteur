package com.sector.secteur.model.pos;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "secteur_application_data_new")
public class SecteurP {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "type")
    private String type;

    @Column(name = "Name")
    private String name;

    @Lob
    @Column(name = "attachmentData")
    private byte[] attachmentData;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private String date;

    public SecteurP(){

    }

    public SecteurP(String destination, String name, String type, byte[] attachmentData, String comment, String date) {

        this.destination = destination;
        this.name = name;
        this.type = type;
        this.attachmentData = attachmentData;
        this.comment = comment;
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
