/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class BillingMenuDAO {

    public ObservableList queryCharges(int confirmation) {

        //Receive Reservation confirmation number
        //Return Observable list of pending Folio Charges
        ObservableList<FolioCharge> result = FXCollections.observableArrayList();

        //Execute Query Here from FolioCharges  Information
        //Build folio charges objects from Results:
        //Return Observalble list of results
        
        result = getFakeData();
        
        
        return result;

    }

    private ObservableList<FolioCharge> getFakeData() {
        ObservableList<FolioCharge> result = FXCollections.observableArrayList();
        result.addAll( new FolioCharge(BillingCode.FOOD, "Steak", LocalDate.now().toString(), 12.00),
            new FolioCharge(BillingCode.MOVIE, "Movie in Room", LocalDate.now().toString(), 10.00),
            new FolioCharge(BillingCode.GIFTSHOP, "Candy", LocalDate.now().toString(), 6.00),
            new FolioCharge(BillingCode.PARKING, "Parking", LocalDate.now().toString(), 50.00)
        );
        
        return result;
    }
}
