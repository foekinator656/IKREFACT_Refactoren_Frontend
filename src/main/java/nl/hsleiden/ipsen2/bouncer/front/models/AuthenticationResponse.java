package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String jwt;
    private UserAccount user;

    public AuthenticationResponse() {}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }
}
