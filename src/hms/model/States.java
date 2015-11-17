/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jgreene
 */
public class States {
    
    private static final ObservableList<String> STATES;
    
    static {
        STATES = FXCollections.observableArrayList();
        STATES.addAll("AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY");
        
    }
    
    public static ObservableList<String> getStates() {
        return STATES;
    }
}
