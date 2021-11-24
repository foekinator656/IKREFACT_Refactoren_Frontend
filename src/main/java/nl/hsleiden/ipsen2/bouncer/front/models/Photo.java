package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;

/**
 * @author Wouter van der Neut
 */
public class Photo implements Serializable {
    private Long pid;
    private String filename;

    public Photo() {
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
