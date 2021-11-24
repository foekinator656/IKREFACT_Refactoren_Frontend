package nl.hsleiden.ipsen2.bouncer.front.views;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.controllers.UserController;
import nl.hsleiden.ipsen2.bouncer.front.models.UserAccount;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;

public class UserShowView implements Observer {
    public Text userNameView;
    public TextField firstNameField;
    public TextField LastNameField;
    public TextField emailField;

    private UserController userController;
    private UserAccount user;

    public UserShowView () {
        this.userController = new UserController(this);
    }

    public void findUserById (Long id) {
        this.userController.findOneUser(id);
    }

    @Override
    public void update(Observable observable) {
        this.user = (UserAccount) observable.getResponse().getResult();

        firstNameField.setText(user.getFirstName());
        LastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        userNameView.setText(user.getUsername());
    }

    public void submitUser(ActionEvent actionEvent) {
        this.user.setEmail(emailField.getText());
        this.user.setLastName(LastNameField.getText());
        this.user.setFirstName(firstNameField.getText());

        this.userController.updateUser(this.user);
    }

    public void backToIndex(ActionEvent actionEvent) {
        ViewManager.getInstance().setShowingScene("Useroverview");
    }
}
