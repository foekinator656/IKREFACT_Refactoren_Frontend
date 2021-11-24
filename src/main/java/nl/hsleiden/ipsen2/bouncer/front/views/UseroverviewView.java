package nl.hsleiden.ipsen2.bouncer.front.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.controllers.UserController;
import nl.hsleiden.ipsen2.bouncer.front.models.Request;
import nl.hsleiden.ipsen2.bouncer.front.models.UserAccount;
import nl.hsleiden.ipsen2.bouncer.front.models.table.SimpleRequest;
import nl.hsleiden.ipsen2.bouncer.front.models.table.SimpleUserAccount;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UseroverviewView implements Observer, Initializable {
    private final UserController userController;

    @FXML
    public TableView<SimpleUserAccount> tabel;

    @FXML
    public TableColumn<SimpleRequest, String> emailCol;

    @FXML
    public TableColumn<SimpleRequest, String> firstNameCol;

    @FXML
    public TableColumn<SimpleRequest, String> lastNameCol;

    @FXML
    public TableColumn<SimpleRequest, String> roleCol;

    @FXML
    public TableColumn<SimpleRequest, Button> actionCol;

    public UseroverviewView () {
        this.userController = new UserController(this);
    }

    @Override
    public void update(Observable observable) {
        List<UserAccount> userAccounts = (List<UserAccount>) observable.getResponse().getResult();
        final ObservableList<SimpleUserAccount> tableData = FXCollections.observableArrayList();

        for (UserAccount userAccount: userAccounts) {
            SimpleUserAccount row = new SimpleUserAccount(userAccount);

            this.userController.addActionToButton(row);

            tableData.add(row);
        }

        tabel.setItems(tableData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        this.userController.findAllUsers();
        this.userController.initialiseUserShow();
    }

    public void goToDashBoard(ActionEvent actionEvent) {
        ViewManager.getInstance().addScene("ModeratorDashboard", "Moderator Index");
        ViewManager.getInstance().setShowingScene("Moderator Index");
    }
}
