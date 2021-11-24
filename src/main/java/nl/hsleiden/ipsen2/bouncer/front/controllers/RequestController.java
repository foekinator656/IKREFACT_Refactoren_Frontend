package nl.hsleiden.ipsen2.bouncer.front.controllers;

import nl.hsleiden.ipsen2.bouncer.front.application.Main;
import nl.hsleiden.ipsen2.bouncer.front.interfaces.View;
import nl.hsleiden.ipsen2.bouncer.front.models.Status;
import nl.hsleiden.ipsen2.bouncer.front.repository.RequestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

import nl.hsleiden.ipsen2.bouncer.front.models.Status;
import nl.hsleiden.ipsen2.bouncer.front.repository.RequestRepository;

import java.text.Normalizer;

public class RequestController {
    private RequestRepository requestRepository;

    public <form> void createNewRequest(form form) {

    }
    public void requestQRCode() {


    }
    public void findAllRequests() {

    }
    public void findOneRequest(int id) {
    }
    public void updateRequestStatus(Status status) {

    }
    public void loadOverviewView() {

    }
    public void loadNewView() {

    }
    public void loadShowView() {

    }
    private void  validateRequestForm(Normalizer.Form form){
//        boolean
    }



    public void update(){

    }

    public void registerObserver(View view) {
    }
}
