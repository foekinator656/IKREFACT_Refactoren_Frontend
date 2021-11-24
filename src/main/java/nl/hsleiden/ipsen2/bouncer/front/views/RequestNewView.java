package nl.hsleiden.ipsen2.bouncer.front.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.hsleiden.ipsen2.bouncer.front.controllers.RequestController;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;
import nl.hsleiden.ipsen2.bouncer.front.response.QrCodeResponse;
import nl.hsleiden.ipsen2.bouncer.front.services.QRCodeService;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Stef Haring
 */
public class RequestNewView implements Observer, Initializable {
    @FXML TextField fName;
    @FXML TextField lName;
    @FXML TextField eMail;
    @FXML DatePicker dateOfBirth;
    @FXML Text fileNameDisplay;
    @FXML Button sendRequestButton;
    @FXML Text fieldErrorPrompt;
    @FXML ImageView qrCodeView;

    File uploadedImage;

    private final RequestController requestController;
    private QRCodeService qrCodeService;
    private Image qrCodeImage;
    private List<String> imageExtArray = new ArrayList<>();

    public RequestNewView() {
        this.requestController = new RequestController(this);
        this.qrCodeService = new QRCodeService();

        imageExtArray.add("png");
        imageExtArray.add("jpg");
        imageExtArray.add("jpeg");
    }

    /**
     * validate input values,
     * if valid; send request with requestcontroller
     */
    @FXML
    public void getRequestInput(ActionEvent event) {
        String fName = this.fName.getText();
        String lName = this.lName.getText();
        String eMail = this.eMail.getText();
        LocalDate dateOfBirth = this.dateOfBirth.getValue();
        File uploadedImage = this.uploadedImage.getAbsoluteFile();

        if (verify_fName(fName) && verify_lName(lName) && verify_eMail(eMail) && verify_birthDate(dateOfBirth) && verify_imageFile(uploadedImage)){
            requestController.submitRequest(uploadedImage, fName, lName, eMail, dateOfBirth.toString());
        }
    }

    /**
     * Opens the file chooser window, so a file can be uploaded
     */
    @FXML
    public void fileChooserWindow(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        if (file != null) {
            fileNameDisplay.setText(file.getName());
        }
        uploadedImage = file;
    }

    /**
     * Makes a larger window so the user can make a bigger picture
     */
    @FXML
    public void openQRcodeWindow(ActionEvent event) {
        ImageView qr = new ImageView();
        qr.minWidth(500);
        qr.minHeight(500);
        qr.setImage(qrCodeImage);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(qr);

        Scene secondScene = new Scene(secondaryLayout, 500, 500);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("QR Code");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);

        newWindow.show();
    }

    /**
     * Set's the imageview to the QrCode
     */
    private void setQRcodePreview(){
        qrCodeView.setImage(qrCodeImage);
    }

    /**
     * Verifies that a last name has been filled in
     *
     * @param fName the firstName
     * @return TRUE = first name is valid, FALSE = first name is not valid
     */
    private boolean verify_fName(String fName) {
        boolean returnValue = false;

        if(!fName.isEmpty()) {
            if (fName.matches("[a-zA-Z][a-zA-Z ]+")){
                returnValue = true;
                setTextFieldStyle(this.fName, returnValue);
            } else {
                this.fName.setText("");
                setTextFieldStyle(this.fName, returnValue);
            }
        } else {
            this.fName.setText("");
            setTextFieldStyle(this.fName, returnValue);
        }

        return returnValue;
    }

    /**
     * Verifies that a last name has been filled in
     *
     * @param lName the last name filled in by the user
     * @return TRUE = last name is valid, FALSE = last name is invalid
     */
    private boolean verify_lName(String lName) {
        boolean returnValue = false;

        if(!lName.isEmpty()) {
            if (lName.matches("[a-zA-Z][a-zA-Z ]+")){
                returnValue = true;
                setTextFieldStyle(this.lName, true);
            } else {
                returnValue = false;
                this.lName.setText("");
                setTextFieldStyle(this.lName, false);
            }
        } else {
            this.lName.setText("");
            setTextFieldStyle(this.lName, false);
        }

        return returnValue;
    }

    /**
     * Verifies that the given input is a valid email
     *
     * @param eMail the given email by the user
     * @return TRUE = string is valid email, FALSE = string is not a valid email
     */
    private boolean verify_eMail(String eMail) {
        boolean returnValue = false;

        if(!eMail.isEmpty()) {
            if (eMail.matches("[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
                returnValue = true;
                setTextFieldStyle(this.eMail, true);
            } else {
                returnValue = false;
                this.eMail.setText("");
                setTextFieldStyle(this.eMail, false);
            }
        } else {
            this.eMail.setText("");
            setTextFieldStyle(this.eMail, false);
        }

        return returnValue;
    }

    /**
     * Verifies that the given date is older than 18 years
     *
     * @param birthdayInput the input of the user
     * @return TRUE = date > 18 years, FALSE = data < 18 years
     */
    private boolean verify_birthDate(LocalDate birthdayInput) {
        LocalDate today = LocalDate.now();

        boolean returnValue = true;

        if (birthdayInput != null){
            if (!birthdayInput.isBefore(today.minusYears(18)) && !birthdayInput.isAfter(today)) {
                returnValue = false;
            }
        } else {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Verifies the image type and size
     *
     * @param imageFile the file that needs validation
     * @return TRUE = File is correct, FALSE = File is either too large or to small
     */
    private boolean verify_imageFile(File imageFile) {
        boolean returnValue;

        String[] fileNameSplitted = imageFile.getName().split("\\.");
        String extension = fileNameSplitted[fileNameSplitted.length - 1];

        if (!imageExtArray.contains(extension)){
            fieldErrorPrompt.setText("Je bestand moet een PNG, JPG of JPEG bestand zijn");
            returnValue = false;

        } else {
            if (imageFile.length() > 20000000){
                fieldErrorPrompt.setText("Je bestand mag maximaal 20mb groot zijn.");
                returnValue = false;
            } else {
                returnValue = true;
            }
        }

        return returnValue;
    }

    /**
     * Changes the color of the input to red when the input is incorrect.
     * The color is changed back to the original value when the value is correct.
     *
     * @param text the textfield of wich the color needs to be changed.
     * @param value FALSE = red input, TRUE = gray input
     */
    private void setTextFieldStyle(TextField text, boolean value){
        if (!value) {
            text.setStyle("-fx-border-color: #d14b4b; -fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #F7F8FC");
            fieldErrorPrompt.setText(text.getPromptText() + " incorrect");
        } else {
            text.setStyle("-fx-border-color: #e6e6e6; -fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #F7F8FC");
            fieldErrorPrompt.setText("");
        }
    }

    @Override
    public void update (Observable observable) {
        QrCodeResponse uniqueQrCode = (QrCodeResponse) observable.getResponse().getResult();

        this.qrCodeImage = qrCodeService.generateQRCode(uniqueQrCode.getQrCode());
        setQRcodePreview();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.requestController.requestQrCode();
    }
}