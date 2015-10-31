/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Team SLAM
 */
class DB {

    static ObservableList queryArrivals() {
        System.out.println("Creating Arrivals Data");
        ObservableList<Reservation> arrivals = FXCollections.observableArrayList();
        
        try {
        arrivals.add(new ReservationBuilder()
                .setCheckinDate(LocalDate.now())
                .setCheckoutDate(LocalDate.of(2015, 11, 1))
                .setComments("")
                .setCreditCard(new CreditCard("Jon","Greene","Test",1,1))
                .setNumAdults(1)
                .setNumChildren(0)
                .setNumRooms(1)
                .setPaymentType(PaymentTypeCode.CREDITCARD)
                .setRate(177.00)
                .setRateCode(RoomRateCode.RACK)
                .setRoomNumber(105)
                .setRoomType(RoomTypeCode.QUEENNS)
                .setGuest(new ProfileBuilder()
                        .setAddress(new Address())
                        .setEmail("")
                        .setFirstName("Ned")
                        .setLastName("Flanders")
                        .setMemberID(112013)
                        .setNotes("")
                        .setPhone("")
                        .setTitle("")
                        .setVIP(true)
                        .createProfile())
                .setFirstName("Ned")
                .setLastName("Flanders")
                .createReservation()
        );
                arrivals.add(new ReservationBuilder()
                .setCheckinDate(LocalDate.now())
                .setCheckoutDate(LocalDate.of(2015, 11, 1))
                .setComments("Trouble Maker")
                .setCreditCard(new CreditCard("Jon","Greene","Test",1,1))
                .setNumAdults(1)
                .setNumChildren(0)
                .setNumRooms(1)
                .setPaymentType(PaymentTypeCode.CREDITCARD)
                .setRate(177.00)
                .setRateCode(RoomRateCode.RACK)
                .setRoomNumber(105)
                .setRoomType(RoomTypeCode.KINGNS)
                .setGuest(new ProfileBuilder()
                        .setAddress(new Address())
                        .setEmail("")
                        .setFirstName("Jon")
                        .setLastName("Greene")
                        .setMemberID(112013)
                        .setNotes("")
                        .setPhone("")
                        .setTitle("")
                        .setVIP(true)
                        .createProfile())
                .setFirstName("Bart")
                .setLastName("Simpson")
                .createReservation()
        );
        arrivals.add(new ReservationBuilder()
                .setCheckinDate(LocalDate.now())
                .setCheckoutDate(LocalDate.of(2015, 11, 1))
                .setComments("None")
                .setCreditCard(new CreditCard("Jon","Greene","Test",1,1))
                .setNumAdults(1)
                .setNumChildren(0)
                .setNumRooms(1)
                .setPaymentType(PaymentTypeCode.CREDITCARD)
                .setRate(177.00)
                .setRateCode(RoomRateCode.RACK)
                .setRoomNumber(413)
                .setRoomType(RoomTypeCode.QUEENS)
                .setGuest(new ProfileBuilder()
                        .setAddress(new Address())
                        .setEmail("")
                        .setFirstName("Jon")
                        .setLastName("Greene")
                        .setMemberID(112013)
                        .setNotes("")
                        .setPhone("")
                        .setTitle("")
                        .setVIP(true)
                        .createProfile())
                .setFirstName("Marge")
                .setLastName("Simson")
                .createReservation()
        );
        arrivals.add(new ReservationBuilder()
                .setCheckinDate(LocalDate.now())
                .setCheckoutDate(LocalDate.of(2015, 11, 7))
                .setComments("Likes donuts")
                .setCreditCard(new CreditCard("Jon","Greene","Test",1,1))
                .setNumAdults(1)
                .setNumChildren(0)
                .setNumRooms(1)
                .setPaymentType(PaymentTypeCode.CREDITCARD)
                .setRate(177.00)
                .setRateCode(RoomRateCode.RACK)
                .setRoomNumber(104)
                .setRoomType(RoomTypeCode.KINGS)
                .setGuest(new ProfileBuilder()
                        .setAddress(new Address())
                        .setEmail("")
                        .setFirstName("Jon")
                        .setLastName("Greene")
                        .setMemberID(112013)
                        .setNotes("")
                        .setPhone("")
                        .setTitle("")
                        .setVIP(true)
                        .createProfile())
                .setFirstName("Homer")
                .setLastName("Simpson")
                .createReservation()
        );
        } catch (Exception e){
            System.out.println("Could not create data");
        }
        System.out.println("Arrivals Data Created");
        System.out.println(arrivals.toString());
        return arrivals;
    }
    
}
