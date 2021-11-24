package nl.hsleiden.ipsen2.bouncer.front.views;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import nl.hsleiden.ipsen2.bouncer.front.controllers.ModeratorDashboardController;
import nl.hsleiden.ipsen2.bouncer.front.models.Request;
import nl.hsleiden.ipsen2.bouncer.front.models.table.SimpleRequest;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModeratorDashboardView implements Observer, Initializable {
    @FXML
    public TableView<SimpleRequest> tabel;

    @FXML
    public TableColumn<SimpleRequest, String> gebruikersNaamCol;

    @FXML
    public TableColumn<SimpleRequest, String> dateCol;

    @FXML
    public TableColumn<SimpleRequest, Button> actionCol;

    @FXML
    public Text nReqField;

    private ModeratorDashboardController moderatorDashboardController;

    public ModeratorDashboardView () {
        this.moderatorDashboardController = new ModeratorDashboardController(this);
    }

    @Override
    public void update(Observable observable) {
        List<Request> requests = (List<Request>) observable.getResponse().getResult();
        final ObservableList<SimpleRequest> tableData = FXCollections.observableArrayList();

        for (Request request: requests) {
            SimpleRequest row = new SimpleRequest(
                    request.getRequestedBy().getUsername(),
                    request.getCreatedAt().toString(),
                    request.getID());

            this.moderatorDashboardController.addActionToShowButton(row);

            tableData.add(row);
        }

        tabel.setItems(tableData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gebruikersNaamCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        this.moderatorDashboardController.findAll();
    }
}
