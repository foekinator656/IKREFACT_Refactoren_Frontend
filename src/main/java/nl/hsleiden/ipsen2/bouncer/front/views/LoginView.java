package nl.hsleiden.ipsen2.bouncer.front.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nl.hsleiden.ipsen2.bouncer.front.application.RequestManager;
import nl.hsleiden.ipsen2.bouncer.front.controllers.LoginController;
import nl.hsleiden.ipsen2.bouncer.front.models.AuthenticationResponse;
import nl.hsleiden.ipsen2.bouncer.front.models.User;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginView implements Observer {
    @FXML
    public ContextMenu context;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private final LoginController loginController;

    public LoginView () {
        this.loginController = new LoginController(this);
    }

    public void login(ActionEvent actionEvent) {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        if (!loginOK(username)) {
            this.username.setText("");
            this.password.setText("");
        } else {
            loginController.userLogin(username, password);
        }
    }

    private boolean loginOK(String userNameLogin) {
        boolean valid_email = verify_email(userNameLogin);
//        boolean valid_email = true;
        boolean valid_uuid = verify_uuid(userNameLogin);

        return true;
    }

    /**
     * Regex found at https://emailregex.com
     */
    public boolean verify_email(String eMailAdress) {
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(eMailAdress);

        return matcher.matches();
    }

    public boolean verify_uuid(String uuid){
        Pattern pattern = Pattern.compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/\n");
        Matcher matcher = pattern.matcher(uuid);

        return matcher.matches();
    }

    @Override
    public void update(Observable observable) {
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) observable.getResponse().getResult();

        if (authenticationResponse == null) {
            context.getItems().clear();
            context.getItems().add(new MenuItem("Email en/of Wachtwoord onjuist"));
            context.show(username, Side.TOP, 30, 0);
        } else {
            User user = authenticationResponse.getUser();

            RequestManager.setJWT(authenticationResponse.getJwt());

            loginController.redirectUser(user.getRole());
        }
    }
}
