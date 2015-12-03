/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import hms.model.CreditCardType;
import hms.model.CreditCard;
import hms.model.FrontDeskDAO;
import hms.model.Reservation;
import hms.model.ReservationStatus;
import hms.model.RoomStatus;
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
 * @author jgreene
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
    private CreditCard cc;
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
        months = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        years = FXCollections.observableArrayList(2015,2016,2017,2018,2019,
                2020,2021,2022,2023,2024,2025);
        ccTypes = FXCollections.observableArrayList(CreditCardType.AMEX,
                CreditCardType.DISC, CreditCardType.MC, CreditCardType.VISA);
        cbxMonth.setItems(months);
        cbxYear.setItems(years);
        cbxCCType.setItems(ccTypes);
        cbxMonth.setValue(1);
        cbxYear.setValue(2015);
        cbxCCType.setValue(CreditCardType.AMEX);
    }    

    @FXML
    private void onClickOK(ActionEvent event) {
        
        //if invalid entry detected, print error message and exit
//        if (!validateFields()){
//            System.out.println("Error Validating CC");
//            return;
//        }
        
        //Write Results to database
        try {
        
        //CHECKIN
        
        //Register CC to Guest
        String creditCardID = dao.registerCreditCard(reservation.getProfileID(), cc);
        
        int creditCard = Integer.parseInt(creditCardID);
        
        //Update Reservation with CC number and status
        reservation.setCreditCardID(creditCard);
        reservation.setStatus(ReservationStatus.CHECKEDIN);
        dao.updateReservation(reservation);
        
        //Update room to occupied
        dao.setRoomOccupied(reservation.getRoomNumber());
        
        //set the result flag
        result = true;
        
        } catch (Exception e){
            System.out.println("Could not record CC info");
        }
        
        stage.close();
        
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        result = false;
        stage.close();
    }
  
    void setStage(Stage stage) {
        this.stage = stage;
    }
    
    //Not Used
    void setCC(CreditCard cc) {
        this.cc = cc;
        if (cc == null)
            return;
        
        txtCCNumber.setText("");
        txtNameOnCC.setText(cc.getName());
        txtCCID.setText(cc.getCode());
        cbxCCType.setValue(cc.getType());
        cbxMonth.setValue(cc.getExpMonth());
        cbxYear.setValue(cc.getExpYear());
    }
    
    //Creates Credit Card from Form
    CreditCard getCC() {
        return new CreditCard(
                txtNameOnCC.getText(),
                txtCCNumber.getText(),
                txtCCID.getText(),
                cbxCCType.getValue(),
                cbxMonth.getValue(),
                cbxYear.getValue()
        );
    }
    
    //Sets Initial Reservation Information
    void setReservationInformation(Reservation reservation) {
        this.reservation = reservation;
        lblRoomNumber.setText(reservation.getRoomNumber()+"");
        lblLastName.setText(reservation.getLastName());
    }
    
    boolean getResult() {
        return result;
    }
    
    //validate all entry
    private boolean validateFields() {
        
        if (txtNameOnCC.getText().isEmpty() || txtCCNumber.getText().isEmpty() || txtCCID.getText().isEmpty() || cbxCCType.getValue().isEmpty() || 
                cbxMonth.getValue().toString().isEmpty() || cbxYear.getValue().toString().isEmpty() ){
            validatorMessage.setText("Please enter credit card number");
            return false;
        }
        if (luhnTest(txtCCNumber.getText().toString() ) ){
            validatorMessage.setText("Please enter valid card number");            
            return false;
        }
        if ( (cardTypeTest(txtCCNumber.getText().toString()) ).equals("invalid card") ){
            validatorMessage.setText("Unsupported credit card type");            
            return false;
        }else {
            return true;
        }
    }    
    
    //method to test credit card numbers
    public boolean luhnTest(String number){
        //even digits are doubled, odd digits are left alone
        int s1 = 0, s2 = 0;
        //reverse String
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            //takes a character, then returns equivalent numerical value in base 10
            int digit = Character.digit(reverse.charAt(i), 10);
            //for odd digits, they are 1-indexed in the algorithm
            if(i % 2 == 0){
                s1 += digit;
            //add 2 * digit for 0-4, add 2 * digit - 9 for 5-9    
            }else{
                s2 += 2 * digit;
                //if result is greater than or equal to 5, then 9 is subtracted (i.e. 14 is considered 1+4 = 5, or 14-9 = 5)
                if(digit >= 5){
                    s2 -= 9;
                }
            }
            //System.out.println(digit);
        }
        //if mod 10 is zero, card number is valid
        System.out.println("Failed LuhnTest");
        return (s1 + s2) % 10 == 0;
    }
    
    //method to find out credit card type
    public String cardTypeTest(String number){
        String reverse = new StringBuffer(number).reverse().toString();
        
        //Amex has 15 digits
        if (reverse.length() == 15){
            if (reverse.charAt(14) == '3'){
                return "Amex";
            }else
                return "invalid Amex card";
        }
        //Visa has 13 or 16 digits, and starts with 4
        else if ((reverse.length() == 13) && (reverse.charAt(12) == '4')){
            return "Visa";
        }else if ((reverse.length() == 16) && (reverse.charAt(15) == '4')){
            return "Visa";
        }
        //Discover or Mastercard has 16 digits    
        else if ((reverse.length() == 16) && (reverse.charAt(15) == '6')){
            //Mastercard starts with 5
            if (reverse.charAt(15) == '5'){                
                return "Mastercard";
            }else if (reverse.charAt(15) == '6'){
                //6011 Discover
                if ((reverse.charAt(14) == '0') && (reverse.charAt(13) == '1') && (reverse.charAt(12) == '1')){
                    return "Discover";   
                //644 Discover
                }else if ((reverse.charAt(14) == '4') && (reverse.charAt(13) == '4')){
                    return "Discover";
                //65 Discover
                }else if (reverse.charAt(14) == '5'){
                    return "Discover";
                }else
                    return "invalid card";  
            }else
                return "invalid card";
        }else{
            return "invalid card";
        } 
                
    }
}
