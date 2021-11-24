package nl.hsleiden.ipsen2.bouncer.front.models;

import java.io.Serializable;
import java.util.Date;

public class RequestUpdate implements Serializable {
    private long id;
    private Request request;
    private UserAccount updateBy;
    private Date updatedAt;
    private Status newState;

    private RequestUpdate(Request request, UserAccount updateBy, Date updatedAt, Status newState){
        this.request = request;
        this.updateBy = updateBy;
        this.updatedAt = updatedAt;
        this.newState = newState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public UserAccount getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UserAccount updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getNewState() {
        return newState;
    }

    public void setNewState(Status newState) {
        this.newState = newState;
    }
}
