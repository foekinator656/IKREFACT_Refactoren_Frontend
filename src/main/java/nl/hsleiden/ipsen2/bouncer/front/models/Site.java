package nl.hsleiden.ipsen2.bouncer.front.models;

import java.util.Date;

public class Site {
    private long id;
    private String name;
    private String url;
    private UserAccount createdBy;
    private Date createdAt;

    private Site(String name,String url,UserAccount createdBy,Date createdAt){
        this.name = name;
        this.url = url;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Request[] getRequests() {
        return new Request[0];
    }
}
