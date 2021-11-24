package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;

public class Worker extends User implements Serializable {
    private String uid;

    public Worker () {
        super();
    }

    public Worker (Long id, String uid) {
        this.id = id;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
