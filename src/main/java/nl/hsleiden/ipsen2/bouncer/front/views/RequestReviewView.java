package nl.hsleiden.ipsen2.bouncer.front.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.controllers.RequestReviewController;
import nl.hsleiden.ipsen2.bouncer.front.models.Request;
import nl.hsleiden.ipsen2.bouncer.front.models.Status;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;

public class RequestReviewView implements Observer {
    private final RequestReviewController requestReviewController;
    private Request request;

    //Moderator Request
    @FXML
    public Text requestID;
    @FXML
    public Button terugDashboard,stuurBericht;
    @FXML
    public TextField naamSekswerker,emailSekswerker,sekswerkerGeboorteDatum;
    @FXML
    public Button accepteren,weigeren,idVragen;
    @FXML
    public ImageView sekswerkerSelfie;
    @FXML
    public Text naamFoto,groteVanFoto,bestandType,aanmaakDatum,bewerkingsDatum,camera;
    @FXML
    public TextField moderatorBericht;

    public RequestReviewView () {
        this.requestReviewController = new RequestReviewController(this);
    }

    public void loadRequest (Long id) {
        this.requestReviewController.findById(id);
    }

    @Override
    public void update(Observable observable) {
        this.request = (Request) observable.getResponse().getResult();

        this.requestID.setText("Request " + this.request.getID());
        this.naamSekswerker.setText(this.request.getRequestedBy().getUid());
        this.emailSekswerker.setText("unknown");
        this.sekswerkerGeboorteDatum.setText("unknown");
    }

    public void backToDashboard(ActionEvent actionEvent) {
        ViewManager.backToLastScene();
    }

    public void acceptRequest(ActionEvent actionEvent) {
        this.requestReviewController.updateRequestStatus(this.request.getID(), Status.APPROVED);
    }

    public void rejectRequest(ActionEvent actionEvent) {
        this.requestReviewController.updateRequestStatus(this.request.getID(), Status.DECLINED);
    }

    public void modAskForID(ActionEvent actionEvent) {
        this.requestReviewController.updateRequestStatus(this.request.getID(), Status.ACTION_REQUIRED);
    }
}
