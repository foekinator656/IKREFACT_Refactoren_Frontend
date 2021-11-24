package nl.hsleiden.ipsen2.bouncer.front.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.models.Request;
import nl.hsleiden.ipsen2.bouncer.front.models.table.SimpleRequest;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;
import nl.hsleiden.ipsen2.bouncer.front.repository.RequestRepository;
import nl.hsleiden.ipsen2.bouncer.front.views.RequestReviewView;

import java.io.IOException;
import java.util.List;

public class ModeratorDashboardController {
    private List<Request> requests;
    private RequestRepository requestRepository;
    private RequestReviewView requestReviewView;

    public ModeratorDashboardController (Observer observer) {
        this.requestRepository = new RequestRepository();
        this.requestRepository.register(observer);
        this.initializeRequestController();
    }

    public void findAll() {
        this.requestRepository.findAll();
    }

    public void addActionToShowButton (SimpleRequest simpleRequest) {
        simpleRequest.getButton().setOnAction(actionEvent -> {
            this.requestReviewView.loadRequest(simpleRequest.getRequestID());
            ViewManager.setShowingScene("Request Review");
        });
    }

    public void initializeRequestController () {
        FXMLLoader loader = ViewManager.getSceneLoader("ReviewRequest");

        try {
            Parent root = loader.load();
            this.requestReviewView = loader.getController();

            ViewManager.addScene("Request Review", new Scene(root, 1600, 900));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
