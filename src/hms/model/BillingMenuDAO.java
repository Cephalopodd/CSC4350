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
        String sql = "select r.id, g.fname, g.lname, r.rm_num, r.arr, r.dep from guest as g, "
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
                        rs.getString(6))
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
        String sql = "select r.id, g.fname, g.lname, r.rm_num, r.arr, r.dep from guest as g, "
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
                        rs.getString(6))
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
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");
        itemList.add("Parking,Valet Parking is available 24 hrs a day,PARKING,25.00");
        itemList.add("Spa1,Spa package 30 minutes,SPA,50.00");
        itemList.add("Spa2,Spa package 60 minutes,SPA,100.00");
        itemList.add("Spa3,Spa package 120 minutes,SPA,125.00");
        itemList.add("Continental Breakfast,Plain breakfast served up daily from 7:00 to 9:00,FOOD,10.00");
        itemList.add("Deluxe Breakfast,Deluxe breakfast,FOOD,15.00");

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
}
