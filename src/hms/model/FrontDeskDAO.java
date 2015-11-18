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
  
    private void closeAll() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (c != null) c.close();
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

      /**
         *   This method takes a profile, a room , arrival date, departure date
         * creates a reservation
         *  and returns a reservationNumber
         */
    public int createReservation(Profile p, Room r, String arrival, String departure) {
        int confirmation = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            StringBuilder sql = new StringBuilder(
                    "insert into reservation values(null,");
            sql.append(p.getMemberID() + ",")
                .append("'" + arrival + "',")
                .append("'" + departure + "',")
                .append("'rack',1,0,'9999',")
                .append("")
                .append("")
                .append("")
                .append("'" + r.getType() + "',")
                .append("'" + r.getNumber() + "',")
                .append("'PENDING','')")
                .append("");
            System.out.println(sql);
            if (stmt.executeUpdate(sql.toString()) > 0) {
                rs = stmt.executeQuery("select last_insert_rowid()");
                confirmation = rs.getInt(1);
            } 
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
    }
        return confirmation;
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
        System.out.println("updating reservation #" + r.getConfirmation());
        int rowsChanged = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            StringBuilder sql = new StringBuilder("update reservation set ");
                sql.append("arr = '" + r.getCheckinDate() + "',")
                .append("dep = '" + r.getCheckoutDate() + "',")
                .append("roomtype = '" + r.getRoomType() + "',")
                .append("comments = '" + r.getComments() + "',")
                .append("adults = " + r.getNumberAdults() + ",")
                .append("kids = " + r.getNumberChildren() + ",")
                .append("rm_num = " + r.getRoomNumber())
                .append(" where id = " + r.getConfirmation());
            System.out.println(sql);
            rowsChanged += stmt.executeUpdate(sql.toString());
            
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsChanged > 0);
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
            stmt = c.createStatement();
            rowsChanged = stmt.executeUpdate("update reservation "
                + "set cc_last4 = '" + last4 + "' where id = " + confirmation);
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
            stmt = c.createStatement();
            StringBuilder sql = new StringBuilder("update guest set ");
            sql.append("fname = '" + p.getFirstName())
                .append("',lname = '" + p.getLastName())
                .append("',phone = '" + p.getPhoneNumber())
                .append("',email = '" + p.getEmail())
                .append("',addr1 = '" + p.getStreet())
                .append("',addr2 = '" + p.getApt())
                .append("',city = '" + p.getCity())
                .append("',state = '" + p.getState())
                .append("',zip = '" + p.getZip())
                .append("',vip = " + (p.isVIP() ? 1 : 0))
                .append(",notes = '" + p.getNotes())
        	.append("' where id = " + p.getMemberID());
            System.out.println(sql);
            rowsChanged = stmt.executeUpdate(sql.toString());
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return (rowsChanged > 0);
    }

    public int createProfile(Profile p) {
        int profileID = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            StringBuilder sql = new StringBuilder(
                    "insert into guest values(null,");
            sql.append("'" + p.getFirstName() + "',")
                .append("'" + p.getLastName() + "',")
                .append("'" + p.getPhoneNumber() + "',")
                .append("'" + p.getEmail() + "',")
                .append("'" + p.getStreet() + "',")
                .append("'" + p.getApt() + "',")
                .append("'" + p.getCity() + "',")
                .append("'" + p.getState() + "',")
                .append("'" + p.getZip() + "',")
                .append((p.isVIP() ? 1 : 0))
                .append(",'" + p.getNotes() + "')");
            System.out.println(sql);
            if (stmt.executeUpdate(sql.toString()) > 0) {
                rs = stmt.executeQuery("select last_insert_rowid()");
                profileID = rs.getInt(1);
            } 
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
    }
        return profileID;
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

    public ObservableList<Room> queryRoomAvailability(String roomType, 
            String arrivalDate, String departureDate) {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        double cost = 0.0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select rate from rate join roomtype "
                    + "on rate.roomtype like roomtype.beds where roomtype.type like '" 
                    + roomType + "' and ratecode like 'rack'");
            if (rs.next()) cost = rs.getDouble(1);
            
            rs = stmt.executeQuery(
                "select number from room where roomtype = '" + roomType + "' "
                + "except select rm_num from reservation "
                + "where (arr between '"+ arrivalDate + "' and '" + departureDate + "') "
                + "or (dep between '"+ arrivalDate + "' and '" + departureDate + "') "
                + "or ((arr < '"+ arrivalDate + "') and (dep > '" + departureDate + "'))");
            while (rs.next()) {
                rooms.add(new RoomBuilder()
                    .setNumber(rs.getInt("number"))
                    .setCost(cost)
                    .setType(roomType)
                    .createRoom()
                );
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return rooms;
    }

    //Needed for the Reservation Edit
    public Reservation getReservation(int resNo) {
        Reservation res = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from reservation r "
                + "join guest g on r.g_id = g.id where r.id = " + resNo);
            if (rs.next()) {
                res = new ReservationBuilder()
                    .setConfirmation(resNo)
                    .setProfileID(rs.getInt("g.id"))
                    .setFirstName(rs.getString("g.fname"))
                    .setLastName(rs.getString("g.lname"))
                    .setCheckinDate(rs.getString("r.arr"))
                    .setCheckoutDate(rs.getString("r.dep"))
                    .setRoomType(rs.getString("r.roomtype"))
                    .setNumberAdults(rs.getInt("r.adults"))
                    .setNumberChildren(rs.getInt("r.kids"))
                    .setPhoneNumber(rs.getString("g.phone"))
                    .setComments(rs.getString("r.comments"))
                    .createReservation();
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeAll();
        }
        return res;
    }
}	
