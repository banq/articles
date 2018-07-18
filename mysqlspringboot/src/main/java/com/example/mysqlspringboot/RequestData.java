package com.example.mysqlspringboot;


import javax.persistence.*;

@Entity
@Table(name = "resource_table")
public class RequestData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "File_id")
    private String id;

    @Column(name = "Images_path")
    private String images;

    @Column(name = "Text_path")
    private String Contents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }
}