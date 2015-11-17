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
    PreparedStatement ps;
    ResultSet rs;
  
    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            ps.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

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
        if (filters.length() > 0) filters.replace(0, 4, " WHERE");
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

    public boolean createReservation(Reservation reservation) {
        
        //Create single reservation...
        
        return true;
    }
    
    public boolean cancelReservation(int confirmation) {
        System.out.println("deleting reservation #" + confirmation);
        int rowsDeleted = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
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
    
    public CreditCard getCreditCard(int confirmation) {
        CreditCard cc = null; 
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(
                "select g.fname, g.lname, cc.card, cc.ccv, cc.exp from reservation r "
                + "join guest g on g.id = r.g_id "
                + "join cc on cc.g_id = g.id and cc.last4 = r.cc_last4");
            if (rs.next()) {
                String exp = rs.getString(5);
                cc = new CreditCard(
                    rs.getString(1) + " " + rs.getString(2),
                    rs.getString(3),
                    CreditCardType.VISA,
                    rs.getString(4),
                    Integer.parseInt(exp.substring(0,2)),
                    Integer.parseInt(exp.substring(3)) + 2000
                );
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return cc;
    }

    public boolean setCreditCard(int confirmation, CreditCard verifiedCC) {
        String ccn = verifiedCC.getCCNumber();
        String last4 = ccn.substring(ccn.length() - 4);
        System.out.println("changing reservation #" + confirmation +
                " to use card ending in " + last4);
        int rowsChanged = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            ps = c.prepareStatement("update reservation "
                    + "set cc_last4 = '?' where id = ?");
            ps.setString(1, last4);
            ps.setInt(2, confirmation);
            rowsChanged = ps.executeUpdate();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsChanged > 0);
    }

    public Profile getProfile(int profileID) {
        Profile guest = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from guest where id = " + profileID);
            if (rs.next()) {
                guest = parseProfile(rs);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return guest;
    }

    public boolean updateProfile(Profile p) {
        System.out.println("updating guest #" + p.getMemberID());
        int rowsChanged = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            ps = c.prepareStatement("update guest set fname = '?', lname = '?', "
                + "phone = '?', email = '?', addr1 = '?', addr2 = '?', city = '?', "
                + "state = '?', zip = '?', vip = ?, notes = '?', where id = ?");
            ps.setString(1,p.getFirstName());
            ps.setString(2,p.getLastName());
            ps.setString(3,p.getPhoneNumber());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getStreet());
            ps.setString(6,p.getApt());
            ps.setString(7,p.getCity());
            ps.setString(8,p.getState());
            ps.setString(9,p.getZip());
            ps.setInt(10,(p.isVIP() ? 1 : 0));
            ps.setString(11,p.getNotes());
            ps.setInt(12,p.getMemberID());
            rowsChanged = ps.executeUpdate();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsChanged > 0);
    }

    public boolean createProfile(Profile p) {
        int rowsInserted = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            ps = c.prepareStatement("insert into guest "
                    + "values(null,'?','?','?','?','?','?','?','?','?',?,'?')");
            ps.setString(1,p.getFirstName());
            ps.setString(2,p.getLastName());
            ps.setString(3,p.getPhoneNumber());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getStreet());
            ps.setString(6,p.getApt());
            ps.setString(7,p.getCity());
            ps.setString(8,p.getState());
            ps.setString(9,p.getZip());
            ps.setInt(10,(p.isVIP() ? 1 : 0));
            ps.setString(11,p.getNotes());
            rowsInserted = ps.executeUpdate();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsInserted > 0);
    }
    
    public ObservableList queryProfiles(ProfileSearchDTO dto) {
        System.out.println("querying profiles");
        ObservableList<Profile> profiles = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            StringBuilder filters = new StringBuilder();
            if (!dto.getMemberID().equals("")) {
                filters.append(" AND id = " + Integer.parseInt(dto.getMemberID()));
            }
            System.out.println("member id");
            if (!dto.getFirstName().equals("")) {
                filters.append(" AND fname like '" + dto.getFirstName() + "'");
            }
            System.out.println("first");
            if (!dto.getLastName().equals("")) {
                filters.append(" AND lname like '" + dto.getLastName() + "'");
            }
            System.out.println("last");
            if (!dto.getPhoneNumber().equals("")) {
                filters.append(" AND phone like '" + dto.getPhoneNumber() + "'");
            }
            System.out.println("phone");
            if (!dto.getEmail().equals("")) { 
                filters.append(" AND email like '" + dto.getEmail() + "'");
            }
            System.out.println("email");
            if (filters.length() > 0) filters.replace(0, 4, " WHERE");
            System.out.println("where");
            System.out.println(filters);
			
            rs = stmt.executeQuery("select * from guest " + filters);
            System.out.println("test 4");
            while (rs.next()) {
                profiles.add(parseProfile(rs));
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return profiles;
    }

	private Profile parseProfile(ResultSet rs) throws Exception {
            Profile guest = new ProfileBuilder()
                .setMemberID(rs.getInt("id"))
                .setFirstName(rs.getString("fname"))
                .setLastName(rs.getString("lname"))
                .setPhoneNumber(rs.getString("phone"))
                .setEmail(rs.getString("email"))
                .setStreet(rs.getString("addr1"))
                .setApt(rs.getString("addr2"))
                .setCity(rs.getString("city"))
                .setState(rs.getString("state"))
                .setZip(rs.getString("zip"))
                .setVIP(rs.getBoolean("vip"))
                .setNotes(rs.getString("notes"))
                .createProfile();
            return guest;
    }
}