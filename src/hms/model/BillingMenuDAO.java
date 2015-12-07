/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class BillingMenuDAO {

    Connection c;
    Statement stmt;
    ResultSet rs;

    private void closeAll() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ObservableList<FolioCharge> queryCharges(int confirmation) {

        //Receive Reservation confirmation number
        //Return Observable list of pending Folio Charges
        ObservableList<FolioCharge> result = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("select id, code, descrip, date, amount "
                    + "from charge where r_id = " + confirmation);
            while (rs.next()) {
                result.add(new FolioCharge(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("descrip"),
                        rs.getString("date"),
                        rs.getDouble("amount")));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();
        }
        return result;
    }

    public ObservableList<BillingMenuDTO> queryCurrentGuests() {

        ObservableList<BillingMenuDTO> result = FXCollections.observableArrayList();
        String sql = "select r.id, g.fname, g.lname, r.rm_num, r.arr, r.dep,"
                + " r.cc_last4, r.roomtype, r.ratecode from guest as g, "
                + "reservation as r where g.id=r.g_id and r.status like 'CHECKEDIN'";

        System.out.println(sql);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(new BillingMenuDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9))
                );
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();
        }
        return result;
    }

    public ObservableList<BillingMenuDTO> queryPastGuests() {

        ObservableList<BillingMenuDTO> result = FXCollections.observableArrayList();
        String sql = "select r.id, g.fname, g.lname, r.rm_num, r.arr, r.dep,"
                + " r.cc_last4, r.roomtype, r.ratecode from guest as g, "
                + "reservation as r where g.id=r.g_id and r.status like 'CHECKEDOUT'";

        System.out.println(sql);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(new BillingMenuDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9))
                );
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();
        }
        return result;
    }

    public ObservableList<Item> queryItems() {

        ObservableList result = FXCollections.observableArrayList();

        ArrayList<String> itemList = new ArrayList();
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Meeting Room A, 10000 sq. ft of meeting space,OTHER,800.00");
        itemList.add("Meeting Room B, 1000 sq. ft of meeting space,OTHER,400.00");
        itemList.add("Meeting Room C, 500 sq. ft of meeting space,OTHER,100.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        
        itemList.add("Printing Services,Print to our business center printer,OTHER, 5.00");
        itemList.add("Internet,Internet access code,SPA,5.00");
        itemList.add("Resort Fee,Standard Fee for gusets,RESORTFEE,15.00");
        itemList.add("Movie,In room movie,MOVIE,10.00");

        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Coke,Can of soda,FOOD,2.00");
        itemList.add("Diet-Coke,Can of soda,FOOD,2.00");
        itemList.add("Sprite,Can of soda,FOOD,2.00");

        itemList.add("Wall Street Journal,Newspaper,GIFTSHOP,1.00");
        itemList.add("New York Times,Newspaper,GIFTSHOP,1.00");
        itemList.add("Brush/Comb,Toiletries,GIFTSHOP,4.00");

        for (String s : itemList) {
            String[] sa = s.split(",");
            Item item = new Item(sa[0], sa[1], sa[2], Double.parseDouble(sa[3]));
            result.add(item);
        }

        return result;
    }

    public boolean addCharge(int confirmationNumber, FolioCharge dto) {
        boolean result = false;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = String.format(
                    "insert into charge values (null, %d, '%s', '%s', '%s', %.2f)",
                    confirmationNumber,
                    dto.getCode(),
                    dto.getDescription(),
                    dto.getDate(),
                    dto.getAmount());

            System.out.println(sql);
            if (stmt.executeUpdate(sql) > 0) {
                rs = stmt.executeQuery("select last_insert_rowid()");
                if (rs.getInt(1) > 0) {
                    result = true;
                }
            }
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();

            return result;
        }

    }

    public boolean delCharge(int chargeId) {
        int rowsDeleted = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = String.format(
                    "delete from charge where id=%d", chargeId);

            System.out.println(sql);

            rowsDeleted = stmt.executeUpdate(sql);

            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();

        }

        return (rowsDeleted > 0);

    }

    public boolean deleteRoomDays(int confirmation) {
        int rowsDeleted = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = String.format(
                    "delete from charge where r_id=%d and code like '%s'",
                    confirmation, BillingCode.ROOM);

            System.out.println(sql);

            rowsDeleted = stmt.executeUpdate(sql);
            System.out.println("Deleted " + rowsDeleted + "rows");
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();
        }
        return (rowsDeleted > 0);

    }

    public boolean checkOutGuest(int confirmation, int roomNumber, double amount, String cc_last4) {
        boolean result = false;
        FrontDeskDAO fdDao = new FrontDeskDAO();

        try {
            FolioCharge charge = new FolioCharge(0,
                    BillingCode.OTHER,
                    "Billed to CC ending in " + cc_last4,
                    LocalDate.now().toString(),
                    amount * -1);

            result = addCharge(confirmation, charge);
            result = fdDao.setRoomUnOccupied(roomNumber);
            result = fdDao.updateReservationStatus(confirmation, ReservationStatus.CHECKEDOUT);
        } catch (Exception e) {
            System.out.println("Error checking out the guest");
        }
        return result;
    }

    public double getRoomRate(String roomtype, String ratecode) {

        double rate = 0;
        String sql = String.format(
                "select rate.rate from rate,roomtype where rate.roomtype "
                + "like roomtype.beds and "
                + "rate.ratecode like '%s' and "
                + "roomtype.type like '%s'", ratecode, roomtype);
        System.out.println(sql);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:hms.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rate = rs.getFloat(1);
            }
            System.out.println("Rate:" + rate);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeAll();
        }
        return rate;
    }
}
