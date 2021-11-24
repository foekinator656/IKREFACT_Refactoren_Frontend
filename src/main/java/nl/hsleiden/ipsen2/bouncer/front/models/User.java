package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;

public class User implements Serializable {
    protected Long id;
    protected Site site;
    protected String username;
    protected Role role;

    public User () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isGranted(Role role) {
        return this.getRole() == role;
    }

    public boolean isGrantedMinimum(Role role) {
        return this.getRole().getValue() >= role.getValue();
    }
}
