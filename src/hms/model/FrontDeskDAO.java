/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import hms.model.ReservationBuilder;
import hms.model.Reservation;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jgreene
 */
public class FrontDeskDAO {

    public ObservableList queryArrivals(FrontDeskArrivalsDTO dto) {
        //This should accept a confirmation number
        //This should delete the reservation from the db
        //This should return T/F outcome

        ObservableList<Reservation> result = FXCollections.observableArrayList();

        //Execute Query Here from FrontDeskArrivalsDTO  Information
        
        //Build Reservation objects from Results:
        
        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());
                result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());
        
                result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Checked In")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("CheckedIn")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

       
        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Checked Out")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());
                result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());
        
                result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());

        result.add(new ReservationBuilder()
                .setFirstName("Test")
                .setLastName("User")
                .setRoomNumber(105)
                .setRoomType("Queen NS")
                .setRoomRate(129.12)
                .setNumberAdults(1)
                .setNumberChildren(4)
                .setCheckinDate(LocalDate.now().toString())
                .setCheckoutDate(LocalDate.now().toString())
                .setComments("No Comment")
                .setStatus("Pending")
                .setCompanyName("My Company")
                .setGroupName("My Group")
                .setConfirmation(12345)
                .createReservation());
        
        //Return Observalble list of results
        return result;
    }

    public boolean cancelReservation(int confirmation) {
        //This should accept a confirmation number
        //This should delete the reservation from the db
        //This should return T/F outcome
        return true;
    }

    public boolean updateReservation(Reservation r) {
        //This should accept a reservation
        //This should update the reservation
        //This should return T/F outcome
        return true;
    }

}

