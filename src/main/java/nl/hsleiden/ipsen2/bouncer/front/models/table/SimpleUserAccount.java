package nl.hsleiden.ipsen2.bouncer.front.models.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import nl.hsleiden.ipsen2.bouncer.front.models.UserAccount;

public class SimpleUserAccount {
    private Long id;
    private SimpleStringProperty email;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty role;
    private Button button;

    public SimpleUserAccount(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.email = new SimpleStringProperty(userAccount.getEmail());
        this.firstName = new SimpleStringProperty(userAccount.getFirstName());
        this.lastName = new SimpleStringProperty(userAccount.getLastName());
        this.role = new SimpleStringProperty(userAccount.getRole().toString());
        this.button = new Button("Bekijk");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
