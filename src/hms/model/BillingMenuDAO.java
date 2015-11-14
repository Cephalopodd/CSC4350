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

    public ObservableList queryArrivals(BillingMenuDTO dto) {

        ObservableList<FolioCharge> result = FXCollections.observableArrayList();

        //Execute Query Here from FolioCharges  Information
        //Build BillingMenu objects from Results:
        //Return Observalble list of results
        return result;

    }
}
