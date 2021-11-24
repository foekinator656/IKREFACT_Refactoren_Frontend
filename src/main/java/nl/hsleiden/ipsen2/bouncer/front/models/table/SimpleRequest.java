package nl.hsleiden.ipsen2.bouncer.front.models.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class SimpleRequest {
    private SimpleStringProperty username;
    private SimpleStringProperty createdAt;
    private Button button;
    private Long requestID;

    public SimpleRequest (String username, String createdAt, Long requestID) {
        this.username = new SimpleStringProperty(username);
        this.createdAt = new SimpleStringProperty(createdAt);
        this.button = new Button("Bekijk");
        this.requestID = requestID;
    }

    public String getUsername() {
        return username.get();
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public Button getButton() {
        return button;
    }

    public Long getRequestID() {
        return requestID;
    }
}
