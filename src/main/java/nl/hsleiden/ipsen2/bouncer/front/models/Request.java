package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Request implements Serializable {
    private long id;
    private Worker requestedBy;
    private Date createdAt;

    public Request () {

    }
    
    public Request(Long id, Worker requestedBy, Date createdAt){
        this.createdAt = createdAt;
        this.requestedBy = requestedBy;
        this.id = id;
    }
    
    public long getID(){
        return id;
    }

    public Worker getRequestedBy(){
        return requestedBy;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void getStatus(){}

    /*
     * TEMP: aanmaken van kunstmatige lijst van requests
     */
    public static List<Request> makeRequestList(int nMembers){

        List<Request> generatedList = new ArrayList<>();
        for (int count = 0; count < nMembers; count++) {
            Long id = Long.valueOf(count* 10L);
            UUID uuid = UUID.randomUUID();
            Worker requestedBy = new Worker(id+1,uuid.toString());
            Date createdAt = new Date(System.currentTimeMillis());
            Request request = new Request(id,requestedBy,createdAt);
            generatedList.add(request);
        }

        return generatedList;
    }
}
