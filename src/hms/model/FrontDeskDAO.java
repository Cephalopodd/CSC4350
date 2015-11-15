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
import java.sql.*;

/**
 *
 * @author jgreene
 */
public class FrontDeskDAO {
    
    Connection c;
    Statement stmt;
    ResultSet rs;
  
    public ObservableList queryArrivals(FrontDeskArrivalsDTO dto) {
        
        ObservableList<Reservation> result = FXCollections.observableArrayList();
        String sql = "select g.fname, g.lname, r.rm_num, r.roomtype, r2.rate, "
            + "r.adults, r.kids, r.arr, r.dep, r.comments, r.status, r.id from "
            + "reservation r join guest g on r.g_id = g.id join roomtype t on "
            + "t.type like r.roomtype join rate r2 on r.ratecode like r2.ratecode "
            + "AND t.beds like r2.roomtype";
        StringBuilder filters = new StringBuilder();
        if (!dto.getFirstName().equals("")) {
            filters.append(" AND g.fname like '").append(dto.getFirstName()).append("'");
        }
        if (!dto.getLastName().equals("")) {
            filters.append(" AND g.lname like '").append(dto.getLastName()).append("'");
        }
        if (!dto.getConfirmation().equals("")) {
            filters.append(" AND r.id = ").append(dto.getConfirmation());
        }
        if (!dto.getPhoneNumber().equals("")) {
            filters.append(" AND g.phone like '").append(dto.getPhoneNumber()).append("'");
        }
        if (!dto.getArrivalDate().equals("")) {
            filters.append(" AND r.arr like '").append(dto.getArrivalDate()).append("'");
        }
        if (!dto.getDepartureDate().equals("")) {
            filters.append(" AND r.dep like '").append(dto.getDepartureDate()).append("'");
        }
        filters.replace(0, 4, " WHERE");
        System.out.println(sql + filters);
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql + filters);
            while (rs.next()) {
                result.add(new ReservationBuilder()
                .setFirstName(rs.getString(1))
                .setLastName(rs.getString(2))
                .setRoomNumber(rs.getInt(3))
                .setRoomType(rs.getString(4))
                .setRoomRate(rs.getDouble(5))
                .setNumberAdults(rs.getInt(6))
                .setNumberChildren(rs.getInt(7))
                .setCheckinDate(rs.getString(8))
                .setCheckoutDate(rs.getString(9))
                .setComments(rs.getString(10))
                .setStatus(rs.getString(11))
                .setCompanyName("")
                .setGroupName("")
                .setConfirmation(rs.getInt(12))
                .createReservation());        
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return result;
    }

    public boolean cancelReservation(int confirmation) {
        int rowsDeleted = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rowsDeleted = stmt.executeUpdate("delete from reservation where id = " 
                        + confirmation);
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsDeleted > 0);
    }

    public boolean updateReservation(Reservation r) {
        //This should accept a reservation
        //This should update the reservation
        //This should return T/F outcome
        return true;
    }
    
    
    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}

