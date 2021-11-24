package nl.hsleiden.ipsen2.bouncer.front.models;

import java.util.Date;

public class Image {
    private long id;
    private Request request;
    private String hash;
    private String path;
    private String extension;
    private Date uploadedAt;

    private Image(long id,Request request, String hash, String path, String extension, Date uploadedAt){
        this.id = id;
        this.request = request;
        this.hash = hash;
        this.path = path;
        this.extension = extension;
        this.uploadedAt = uploadedAt;
    }

    public void calculateHash(){

    }
    public void compareHash(String hash){

    }


}
