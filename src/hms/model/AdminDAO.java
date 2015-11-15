package hms.model;

public class AdminDAO {
    
    //Query that accesses the database and get toatalSales of the current day.
    //Ex. If the total sales is 123.33 for %Todays Date%, query will return 123.33.
    //Let me know if it is easier to return string vs double or int.
    public double getTotalSales(double totalSales) {
        //Please write query to return the total Sales for the current day.
        double sales = 123.23;
        return sales;
    }
    
    //Query that returns the an int value of check outs that are schedule for the day.
    //Ex. If there are 10 check out schedules for %Todays Date%, the query will return a value of 10.
    public int getCheckOut(int checkOutToday){
        checkOutToday = 5;
        return checkOutToday;
    }
    
    //Query that returns the an int value of check ins that are schedule for the day.
    //Ex. If there are 10 check in schedules for %Todays Date%, the query will return a value of 10.
    public int getCheckIn(int checkInToday){
        checkInToday = 20;
        return checkInToday;
    }
}
