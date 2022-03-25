package com.example.eduorigin.models;

public class ResponseModelBookLibraryBackup {

    String id,name,description,image,pdf;

    public ResponseModelBookLibraryBackup(String id, String name, String description, String image, String pdf) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.pdf = pdf;
    }

    public ResponseModelBookLibraryBackup() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
