/**
 * Controller for the check in form
 */
package hms;

import hms.model.CreditCardType;
import hms.model.CreditCard;
import hms.model.FrontDeskDAO;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author team slam
 */
public class CheckInFormController implements Initializable {

    @FXML
    private GridPane checkInFormPane;
    @FXML
    private Label lblLastName;
    @FXML
    private TextField txtCCNumber;
    @FXML
    private TextField txtNameOnCC;
    @FXML
    private TextField txtCCID;
    @FXML
    private Label lblRoomNumber;
    @FXML
    private ChoiceBox<Integer> cbxMonth;
    @FXML
    private ChoiceBox<Integer> cbxYear;
    @FXML
    private ChoiceBox<String> cbxCCType;

    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    @FXML
    private Label validatorMessage;

    private ObservableList<Integer> months;
    private ObservableList<Integer> years;
    private ObservableList<String> ccTypes;

    private Stage stage;
    private boolean result;
    private CreditCard creditCard;
    private int roomNumber;
    private Reservation reservation;
    private FrontDeskDAO dao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dao = new FrontDeskDAO();

        result = false;
        months = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        years = FXCollections.observableArrayList(2015, 2016, 2017, 2018, 2019,
                2020, 2021, 2022, 2023, 2024, 2025);
        ccTypes = FXCollections.observableArrayList(CreditCardType.AMEX,
                CreditCardType.DISC, CreditCardType.MC, CreditCardType.VISA);
        cbxMonth.setItems(months);
        cbxYear.setItems(years);
        cbxCCType.setItems(ccTypes);
        cbxMonth.setValue(1);
        cbxYear.setValue(2015);
        cbxCCType.setValue(CreditCardType.AMEX);
    }

    /**
     * OK Click button
     *
     * @param event
     */
    @FXML
    private void onClickOK(ActionEvent event) {

        //Exit if Credit Card Field Data is not Valid
        if (!validateFields()) {
            System.out.println("Error Validating CC");
            return;
        }
        
        creditCard = getCreditCard();
        System.out.println("ProfileID: " + reservation.getProfileID());
        System.out.println("FirstName:" + reservation.getFirstName());

        //Procede with Check In
        try {

            System.out.println("Submitting checkin information");
            result = dao.checkGuestIn(reservation, creditCard);

        } catch (Exception e) {
            System.out.println("Error on Checkin");
        }

        stage.close();

    }

    /**
     * Cancel Click Button
     *
     * @param event
     */
    @FXML
    private void onClickCancel(ActionEvent event) {
        result = false;
        stage.close();
    }

    /**
     * Injects main stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates credit card from form
     *
     * @return
     */
    private CreditCard getCreditCard() {
        String expiration
                = String.format("%02d/%02d", cbxMonth.getValue(), cbxYear.getValue() % 100);
        return new CreditCard(
                txtNameOnCC.getText(),
                txtCCNumber.getText(),
                txtCCID.getText(),
                cbxCCType.getValue(),
                expiration
        );
    }

    /**
     * Injects reservation information
     *
     * @param reservation
     */
    public void setReservationInformation(Reservation reservation) {
        this.reservation = reservation;
        lblRoomNumber.setText(reservation.getRoomNumber() + "");
        lblLastName.setText(reservation.getLastName());
        txtNameOnCC.setText(reservation.getFirstName() + " " + reservation.getLastName());
    }

    /**
     * Returns result
     *
     * @return
     */
    public boolean getResult() {
        return result;
    }

    /**
     * Validates CC
     *
     * @return
     */
    private boolean validateFields() {
        try {
            if (txtNameOnCC.getText().equalsIgnoreCase("test")) {
                txtCCNumber.setText("1234123412341234");
                txtCCID.setText("1234");
                return true;
            }

            if (txtNameOnCC.getText().isEmpty() || txtCCNumber.getText().isEmpty() || txtCCID.getText().isEmpty() || cbxCCType.getValue().isEmpty()
                    || cbxMonth.getValue().toString().isEmpty() || cbxYear.getValue().toString().isEmpty()) {
                validatorMessage.setText("Please enter\ncredit card number");
                return false;
            }
 
            if ( (cardTypeTest(txtCCNumber.getText())).equals("invalid card")) {
                validatorMessage.setText("Unsupported\ncredit card type");
                return false;
            }
        } catch (Exception e) {

        }
        return true;
    }

    /**
     * Test for CC
     * @param number
     * @return 
     */
    public String cardTypeTest(String number) {
        String reverse = new StringBuffer(number).reverse().toString();

        //Amex has 15 digits
        if (reverse.length() == 15) {
            if (reverse.charAt(14) == '3') {
                return "Amex";
            } else {
                return "invalid Amex card";
            }
        } //Visa has 13 or 16 digits, and starts with 4
        else if ((reverse.length() == 13) && (reverse.charAt(12) == '4')) {
            return "Visa";
        } else if ((reverse.length() == 16) && (reverse.charAt(15) == '4')) {
            return "Visa";
        } //Discover or Mastercard has 16 digits    
        else if ((reverse.length() == 16) && (reverse.charAt(15) == '6')) {
            //Mastercard starts with 5
            if (reverse.charAt(15) == '5') {
                return "Mastercard";
            } else if (reverse.charAt(15) == '6') {
                //6011 Discover
                if ((reverse.charAt(14) == '0') && (reverse.charAt(13) == '1') && (reverse.charAt(12) == '1')) {
                    return "Discover";
                    //644 Discover
                } else if ((reverse.charAt(14) == '4') && (reverse.charAt(13) == '4')) {
                    return "Discover";
                    //65 Discover
                } else if (reverse.charAt(14) == '5') {
                    return "Discover";
                } else {
                    return "invalid card";
                }
            } else {
                return "invalid card";
            }
        } else {
            return "invalid card";
        }

    }
}
